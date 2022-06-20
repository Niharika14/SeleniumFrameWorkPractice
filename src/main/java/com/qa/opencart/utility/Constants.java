package com.qa.opencart.utility;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String LOGIN_PAGE_TITLE = "Login - My Store";
    public static final String LOGIN_PAGE_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    public static final String ACCOUNT_PAGE_TITLE = "My account - My Store";
    public static final List<String> ACCOUNT_PAGE_SECTION_VALUES =  Arrays.asList("ORDER HISTORY AND DETAILS",
            "MY CREDIT SLIPS",
            "MY ADDRESSES",
            "MY PERSONAL INFORMATION",
            "MY WISHLISTS");
    public static final int SEARCH_PAGE_RESULT_COUNT = 7;
    public static final String SEARCH_PAGE_RESULT_MESSAGE = "7 results have been found.";
    public static final List<String> SEARCH_PAGE_RESULT_VALUES = Arrays.asList("Printed Summer Dress", "Printed Dress", "Printed Summer Dress", "Printed Chiffon Dress", "Printed Dress", "Faded Short Sleeve T-shirts", "Blouse");
    public static final int PRODUCT_IMAGE_COUNT = 2;
    public static final boolean PRODUCT_PRICE_DISCOUNT =true;
    public static final List<String> PRODUCT_PRICE = Arrays.asList("$16.40", "-20%", "$20.50");
}
