package com.tip.customer.controller.helper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tip.customer.model.CustomerData;

@Component
public class CustomerControllerHelper{
	public Map<String, String> setCustomerFilter(CustomerData customerData) {
	Map<String, String> fields = new HashMap<>(0);
	if(customerData.getCustomerSkey() != null)
	{
		fields.put("customer_skey", customerData.getCustomerSkey().toString());
	}
	if(customerData.getCustomerNr() != null)
	{
		fields.put("customer_nr", customerData.getCustomerNr().toString());
	}
	if(customerData.getCustomerNumberCombi() != null)
	{
		fields.put("customer_number_combi", customerData.getCustomerNumberCombi().toString());
	}
	if(customerData.getCustomerNameAbbreviation() != null && !customerData.getCustomerNameAbbreviation().isEmpty())
	{
		fields.put("customer_name_abbreviation", customerData.getCustomerNameAbbreviation());
	}
	if(customerData.getCustomerName() != null && !customerData.getCustomerName().isEmpty())
	{
		fields.put("customer_name", customerData.getCustomerName());
	}
	if(customerData.getName() != null && !customerData.getName().isEmpty())
	{
		fields.put("name", customerData.getName());
	}
	if(customerData.getUpperName() != null && !customerData.getUpperName().isEmpty())
	{
		fields.put("upper_name", customerData.getUpperName());
	}
	if(customerData.getSfdcExternalAccountId() != null)
	{
		fields.put("sfdc_external_account_id", customerData.getSfdcExternalAccountId().toString());
	}
	if(customerData.getCustomerRegistrationNr() != null && !customerData.getCustomerRegistrationNr().isEmpty())
	{
		fields.put("customer_registration_nr", customerData.getCustomerRegistrationNr());
	}
	/*if(customerData.getCustomerSalesPhone() != null)
	{
		fields.put("customer_sales_phone", customerData.getCustomerSalesPhone().toString());
	}
	if(customerData.getCustomerCollectionPhone() != null)
	{
		fields.put("customer_collection_phone", customerData.getCustomerCollectionPhone().toString());
	}
	if(customerData.getCustomerOperationsPostalCd() != null && !customerData.getCustomerOperationsPostalCd().isEmpty())
	{
		fields.put("customer_operations_postal_cd", customerData.getCustomerOperationsPostalCd());
	}
	if(customerData.getCustomerInvoicePostalCd() != null && !customerData.getCustomerInvoicePostalCd().isEmpty())
	{
		fields.put("customer_invoice_postal_cd", customerData.getCustomerInvoicePostalCd());
	}
	if(customerData.getCustomerStreetAddr() != null && !customerData.getCustomerStreetAddr().isEmpty())
	{
		fields.put("customer_street_addr", customerData.getCustomerStreetAddr());
	}
	if(customerData.getCustomerCity() != null && !customerData.getCustomerCity().isEmpty())
	{
		fields.put("customer_city", customerData.getCustomerCity());
	}
	if(customerData.getCustomerAlfaNr() != null && !customerData.getCustomerAlfaNr().isEmpty())
	{
		fields.put("customer_alfa_nr", customerData.getCustomerAlfaNr());
	}
	if(customerData.getPhoneNumber() != null)
	{
		fields.put("phone_number", customerData.getPhoneNumber().toString());
	}
	if(customerData.getAddr() != null && !customerData.getAddr().isEmpty())
	{
		fields.put("addr", customerData.getAddr());
	}
	if(customerData.getCity() != null && !customerData.getCity().isEmpty())
	{
		fields.put("city", customerData.getCity());
	}
	if(customerData.getZipCd() != null && !customerData.getZipCd().isEmpty())
	{
		fields.put("zip_cd", customerData.getZipCd());
	}
	if(customerData.getUpperCity() != null && !customerData.getUpperCity().isEmpty())
	{
		fields.put("upper_city", customerData.getUpperCity());
	}
	if(customerData.getEmailAddress() != null && !customerData.getEmailAddress().isEmpty())
	{
		fields.put("email_address", customerData.getEmailAddress());
	}*/
        return fields;
    }
	
	public Map<String, String> setFilterForCustomerNr(CustomerData customerData) {
		Map<String, String> fields = new HashMap<>(0);		
		if(customerData.getCustomerNr() != null)
		{
			fields.put("customer_nr", customerData.getCustomerNr().toString());
		}
		if(customerData.getCustomerCompanyNr() != null)
		{
			fields.put("customer_company_nr", customerData.getCustomerCompanyNr().toString());
		}
	        return fields;
	    }
}