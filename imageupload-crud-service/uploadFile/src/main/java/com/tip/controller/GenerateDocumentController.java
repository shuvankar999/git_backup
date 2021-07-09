package com.tip.controller;

import com.tip.api.GenerateDocuRequest;
import com.tip.api.GenerateDocuResponse;
import com.tip.api.PocketMobileServiceService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.soap.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 885155 on 6/22/2017.
 */
@Controller
@RestController
@ComponentScan
public class GenerateDocumentController {
	public GenerateDocuResponse generateDocument(GenerateDocuRequest generateDocuRequest) throws MalformedURLException {

		URL url = new URL("https://www.tip-europe.com/SSO/MTX/PRD/PMALSCheckInServices/PocketMobileService.wsdl");
		PocketMobileServiceService pocketMobileServiceService = new PocketMobileServiceService(url);

		return pocketMobileServiceService.getPocketMobileService().generateDocument(generateDocuRequest);

	}

}
