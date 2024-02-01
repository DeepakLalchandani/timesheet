package com.techsopi.controller.validations;

import com.techsopi.constant.AppConstant;
import com.techsopi.enums.BillingMethod;
import com.techsopi.model.ClientDTO;

import java.util.ArrayList;
import java.util.List;

public final class Validation {

    private Validation() {

    }

    public static boolean validateMobileNumber(String mobileNumber) {

        return mobileNumber.matches(AppConstant.VALIDATE_MOBILE_NUMBER_REGEX);
    }

    public static boolean validateBillingMethod(String billingMethod) {
        return billingMethod.equalsIgnoreCase(BillingMethod.BILLABLE.getValue()) || billingMethod.equalsIgnoreCase(BillingMethod.NONBILLABLE.getValue());
    }

    public static boolean validateEmailId(String emailId) {
        return emailId.matches(AppConstant.VALIDATE_EMAIL_REGEX);
    }

    public static List<String> validateClientDTO(ClientDTO clientDTO) {

        List<String> errors = new ArrayList<>();
        if (!validateBillingMethod(clientDTO.getBillingMethod())) {
            errors.add("Billing method should either be 'billable' or 'nonbillable'");
        } else if (!validateEmailId(clientDTO.getEmailId())) {
            errors.add("Email Id is not valid");
        } else if (!validateMobileNumber(clientDTO.getMobile())) {
            errors.add("Mobile number should contain 10 digits");
        }
        return errors;
    }
}