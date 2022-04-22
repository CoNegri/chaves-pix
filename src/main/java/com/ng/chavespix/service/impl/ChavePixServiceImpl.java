package com.ng.chavespix.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ng.chavespix.dto.AlteracaoChaveRequest;
import com.ng.chavespix.dto.ConsultaChaveRequest;
import com.ng.chavespix.dto.InsercaoChaveRequest;
import com.ng.chavespix.dto.response.ChavePixResponse;
import com.ng.chavespix.dto.response.InsercaoResponse;
import com.ng.chavespix.entity.ChavePix;
import com.ng.chavespix.entity.Conta;
import com.ng.chavespix.enums.TipoPessoa;
import com.ng.chavespix.exception.EntidadeNotFoundException;
import com.ng.chavespix.exception.ValidacaoException;
import com.ng.chavespix.repository.ChavePixRepository;
import com.ng.chavespix.service.IChavePixService;

@Service
public class ChavePixServiceImpl implements IChavePixService {

	private final long MAXIMO_CHAVES_PF = 5;
	private final long MAXIMO_CHAVES_PJ = 20;

	private final String CONTA_NOT_FOUND_MSG = "conta não cadastrada no sistema";
	private final String LIMITE_CHAVES_MSG = "a conta já possuí o número máximo de chaves cadastradas";
	private final String CHAVE_CADASTRADA_MSG = "valorChave já cadastrado no sistema";
	private final String CHAVE_NAO_CADASTRADA_MSG = "chave não cadastrada no sistema";
	private final String CHAVE_INATIVADA = "chave já inativada";
	private final String TITULAR_MSG = "não há titular cadastrado para essa conta";

	private ChavePixRepository repository;

	private ContaServiceImpl contaService;

	private TitularServiceImpl titularService;

	@Autowired
	public ChavePixServiceImpl(ChavePixRepository chavePixRepository, ContaServiceImpl contaService,
			TitularServiceImpl titularService) {
		this.repository = chavePixRepository;
		this.contaService = contaService;
		this.titularService = titularService;
	}

	public InsercaoResponse inserirChave(InsercaoChaveRequest request) throws ValidacaoException {

		ChavePix chave = this.repository.findFirstByValorChave(request.getValorChave());

		if (chave == null) {

			Conta conta = this.contaService.obterConta(request.getTipoConta(), request.getNumeroAgencia(),
					request.getNumeroConta());

			if (conta == null) {
				throw new ValidacaoException(CONTA_NOT_FOUND_MSG);
			}

			boolean haChavesDisponiveis = this.verificarChavesDisponiveis(conta);

			if (haChavesDisponiveis) {

				ChavePix novaChave = new ChavePix();
				novaChave.setTipoChave(request.getTipoChave());
				novaChave.setValorChave(request.getValorChave());
				novaChave.setConta(conta);
				novaChave.setNomeExibicao(request.getNomeCorrentista());
				novaChave.setSobrenomeExibicao(request.getSobrenomeCorrentista());
				novaChave.setDataInclusao(new Date());

				String uuid = this.repository.save(novaChave).getId().toString();

				return new InsercaoResponse(uuid);

			} else {
				throw new ValidacaoException(LIMITE_CHAVES_MSG);
			}
		} else {
			throw new ValidacaoException(CHAVE_CADASTRADA_MSG);
		}

	}

	private boolean verificarChavesDisponiveis(Conta conta) throws ValidacaoException {

		TipoPessoa tipoPessoa = this.titularService.obterTipoPessoa(conta);

		if (tipoPessoa == null) {
			throw new ValidacaoException(TITULAR_MSG);
		}

		long qntChaves = this.repository.obterQuantidadeChaves(conta);

		return tipoPessoa == TipoPessoa.pf ? qntChaves < MAXIMO_CHAVES_PF : qntChaves < MAXIMO_CHAVES_PJ;

	}

	public ChavePixResponse alterarChave(AlteracaoChaveRequest request)
			throws EntidadeNotFoundException, ValidacaoException {

		ChavePix chave = this.repository.findFirstById(request.getId());

		if (chave != null) {

			if (chave.getDataInativacao() == null) {

				Conta conta = this.contaService.obterConta(request.getTipoConta(), request.getNumeroAgencia(),
						request.getNumeroConta());

				if (conta == null) {
					throw new ValidacaoException(CONTA_NOT_FOUND_MSG);
				}

				boolean haChavesDisponiveis = true;

				if (conta.getNumeroAgencia() != chave.getConta().getNumeroAgencia()
						|| conta.getNumeroConta() == chave.getConta().getNumeroConta()
						|| conta.getTipoConta() != chave.getConta().getTipoConta()) {
					
					haChavesDisponiveis = this.verificarChavesDisponiveis(conta);
				}

				if (haChavesDisponiveis) {

					chave.setConta(conta);
					chave.setNomeExibicao(request.getNomeCorrentista());
					chave.setSobrenomeExibicao(request.getSobrenomeCorrentista());

					chave = this.repository.save(chave);

					return this.convertToDto(chave, null);

				} else {
					throw new ValidacaoException(LIMITE_CHAVES_MSG);
				}

			} else {
				throw new ValidacaoException(CHAVE_INATIVADA);
			}
		} else {
			throw new EntidadeNotFoundException(CHAVE_NAO_CADASTRADA_MSG);
		}

	}

	public ChavePixResponse inativarChave(String id) throws ValidacaoException, EntidadeNotFoundException {

		ChavePix chave = this.repository.buscarChaveID(id);

		if (chave != null) {

			if (chave.getDataInativacao() == null) {

				chave.setDataInativacao(new Date());

				chave = this.repository.save(chave);

				return this.convertToDto(chave, null);

			} else {
				throw new ValidacaoException(CHAVE_INATIVADA);
			}
		} else {
			throw new EntidadeNotFoundException(CHAVE_NAO_CADASTRADA_MSG);
		}

	}

	public List<ChavePixResponse> buscarChave(ConsultaChaveRequest request) {

		List<ChavePix> chaves = this.repository.consultarChaves(request);
		return chaves.stream().map(c -> this.convertToDto(c, "dd/MM/yyyy")).collect(Collectors.toList());

	}
	
	public ChavePixResponse buscarChavePorId(String id) {
		
		ChavePix cp = this.repository.buscarChaveID(id);
		
		if (cp != null) {
			return this.convertToDto(cp, "dd/MM/yyyy");			
		}
		
		return null;
	}
	
	private ChavePixResponse convertToDto(ChavePix entity, String date_formatt) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  

		ChavePixResponse chaveResponse = new ChavePixResponse();
		chaveResponse.setId(entity.getId().toString());
		chaveResponse.setTipoChave(entity.getTipoChave());
		chaveResponse.setValorChave(entity.getValorChave());
		chaveResponse.setNumeroAgencia(entity.getConta().getNumeroAgencia());
		chaveResponse.setNumeroConta(entity.getConta().getNumeroConta());
		chaveResponse.setTipoConta(entity.getConta().getTipoConta());
		chaveResponse.setNomeCorrentista(entity.getNomeExibicao());
		chaveResponse.setSobrenomeCorrentista(entity.getSobrenomeExibicao());

		if (entity.getDataInclusao() != null) {
			
			if ( date_formatt != null) {
				chaveResponse.setDataInclusao(dateFormat.format(entity.getDataInclusao()));
			} else {
				chaveResponse.setDataInclusao(entity.getDataInclusao().toString());
			}
		}
		
		if (entity.getDataInativacao() != null) {
			
			if ( date_formatt != null) {
				chaveResponse.setDataInativacao(dateFormat.format(entity.getDataInativacao()));
			} else {
				chaveResponse.setDataInativacao(entity.getDataInativacao().toString());
			}
		} else {
			chaveResponse.setDataInativacao("");
		}
		
		if (entity.getSobrenomeExibicao() == null) {
			chaveResponse.setSobrenomeCorrentista("");
		}
		
		return chaveResponse;
	
	}


}
