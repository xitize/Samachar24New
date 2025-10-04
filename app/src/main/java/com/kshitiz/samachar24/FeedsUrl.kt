package com.kshitiz.samachar24

object FeedsUrl {
    @JvmStatic
    val nepaliFeedsUrl: ArrayList<String?>
        get() {
            val urls = java.util.ArrayList<String?>()
            urls.add("https://query.yahooapis.com/v1/public/yql?q=select * from xml where url = 'http://rajmarga.com/feed'&format=json")
            urls.add("https://query.yahooapis.com/v1/public/yql?q=select * from xml where url = 'http://medianp.com/feed'&format=json")
            urls.add("https://query.yahooapis.com/v1/public/yql?q=select * from xml where url = 'http://www.souryadaily.com/feed'&format=json")
            urls.add("https://query.yahooapis.com/v1/public/yql?q=select * from xml where url = 'http://rajdhanidaily.com/feed'&format=json")
            urls.add("https://query.yahooapis.com/v1/public/yql?q=select * from xml where url = 'http://www.bizkhabar.com/feed'&format=json")
            urls.add("https://query.yahooapis.com/v1/public/yql?q=select * from xml where url = 'http://www.souryadaily.com/feed'&format=json")
            urls.add("https://query.yahooapis.com/v1/public/yql?q=select * from xml where url = 'http://np.gorkhapost.com/feed'&format=json")
            urls.add("https://query.yahooapis.com/v1/public/yql?q=select * from xml where url = 'http://www.etajakhabar.com/feed'&format=json")
            urls.add("https://query.yahooapis.com/v1/public/yql?q=select * from xml where url = 'http://www.abhiyan.com.np/feed'&format=json")
            urls.add("https://query.yahooapis.com/v1/public/yql?q=select * from xml where url = 'http://www.newsofnepal.com/feed'&format=json")
            urls.add("https://query.yahooapis.com/v1/public/yql?q=select * from xml where url = 'http://www.samacharpati.com/feed'&format=json")
            urls.add("https://query.yahooapis.com/v1/public/yql?q=select * from xml where url = 'http://everestdainik.com/feed/%27&format=json")
            return urls
        }

    @JvmStatic
    val englishFeedsUrl: ArrayList<String?>
        get() {
            val urls = java.util.ArrayList<String?>()
            urls.add("https://query.yahooapis.com/v1/public/yql?q=select * from xml where url = 'http://english.onlinekhabar.com/feed%27&format=json")
            urls.add("https://query.yahooapis.com/v1/public/yql?q=select * from xml where url = 'http://thehimalayantimes.com/feed%27&format=json")
            urls.add("https://query.yahooapis.com/v1/public/yql?q=select * from xml where url = 'http://www.gorkhapost.com/feed%27&format=json")
            return urls
        }
}