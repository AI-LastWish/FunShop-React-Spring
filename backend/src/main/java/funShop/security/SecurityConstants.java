package funShop.security;

public class SecurityConstants {
	public static final String SIGN_UP_URLS = "/api/users/register/";
	public static final String LOGIN_URLS = "/api/users/login/";
	public static final String PRODUCTS_URL = "/api/products/**";
	public static final String BRANDS_URL = "/api/brands/**";
	public static final String CATEGORIES_URL = "/api/categories/**";
	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final long EXPIRATION_TIME = 900000; // 15minutes = 900 seconds
}