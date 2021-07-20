 The REST API Endpoint Service to provide currency rates.
 The source data gets from "https://app.exchangerate-api.com/"
 This service returns the rate between pairs.
 This service caches constant request result in internal cache implemented with ConcurrentHashMap
 The Cache always contains four pairs: USD-SGD, SGD-USD, UDK-HKD, HKD-USD
 The Cache updates every 1 hour in the background.
 
 Example to get rate of USD-SGD: The endpoint should be: http://localhost:8080/xpanpool/pair?base=USD&target=SGD
 and the response is: {"base_code":"USD","target_code":"SGD","conversion_rate":1.3581,"result":"success","error_type":null}
 which means 1USD = 1.3581SGD
 Provide base country code  and target county code to get the currency rate.
