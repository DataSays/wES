package org.datasays.wes.demo.es.service;

import org.datasays.wes.EsBaseService;
import org.datasays.wes.client.EsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

/**
 * Created by watano on 2016/12/22.
 */
@Service
public class EsService extends EsBaseService {
	private static Logger LOG = LoggerFactory.getLogger(EsService.class);
	@Value("${es.server}")
	@NotNull
	private String esServer;
	@Value("${es.user}")
	private String esUser;
	@Value("${es.pswd}")
	private String esPswd;

	@PostConstruct
	public void init() {
		init(esServer, null, null);
		esHelper.setLogFlag(LOG.isDebugEnabled(), LOG.isDebugEnabled(), LOG.isDebugEnabled());
		esHelper.baseAuth(esUser, esPswd);
	}

	public EsHelper getEsHelper() {
		return esHelper;
	}
}
