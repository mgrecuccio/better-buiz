package eu.mgrtech.better.buiz.views.organization;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

import eu.mgrtech.better.buiz.entities.Organization;

public class OrganizationInfoForm extends FormLayout {

    private static final String VAT_NUMBER = "VAT Number";
    private static final String COMPANY_NAME = "Company Name";
    private static final String START_DATE = "Start Date";
    private static final String ADDRESS = "Address";
    private static final String COMPANY_TYPE = "Company Type";
    private static final String COMPANY_STATUS = "Company Status";
    private static final String EMAIL = "Email Address";
    private static final String GENERAL_MANAGER = "General Manager";

    InfoFormTextField vatNumber = new InfoFormTextField(VAT_NUMBER);
    InfoFormTextField companyName = new InfoFormTextField(COMPANY_NAME);
    InfoFormTextField startDate = new InfoFormTextField(START_DATE);
    InfoFormTextField address = new InfoFormTextField(ADDRESS);
    InfoFormTextField companyType = new InfoFormTextField(COMPANY_TYPE);
    InfoFormTextField companyStatus = new InfoFormTextField(COMPANY_STATUS);
    InfoFormTextField emailAddress = new InfoFormTextField(EMAIL);
    InfoFormTextField generalManager = new InfoFormTextField(GENERAL_MANAGER);

    public OrganizationInfoForm(Organization organization) {
        if(organization != null) {
            vatNumber.setValue(organization.vatNumber());
            companyName.setValue(organization.companyName());
            startDate.setValue(organization.startDate());
            address.setValue(organization.address());
            companyType.setValue(organization.companyType());
            companyStatus.setValue(organization.companyStatus());
            emailAddress.setValue(organization.companyEmail());
            generalManager.setValue(organization.companyGeneralManager());
        }

        add(vatNumber, companyName, startDate, companyStatus, address, companyType, emailAddress, generalManager);
    }

    class InfoFormTextField extends TextField {

        public InfoFormTextField(String label) {
            super(label);
            super.setReadOnly(true);
        }
    }
}
