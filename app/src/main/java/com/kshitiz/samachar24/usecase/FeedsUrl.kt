package com.kshitiz.samachar24.usecase


data class FeedItem(val name: String, val url: String, val image: String)

object FeedsUrl {
    val nepaliFeeds = listOf(
        FeedItem("Online Khabar", "https://onlinekhabar.com/feed", "https://www.onlinekhabar.com/wp-content/uploads/2017/05/logo-mobile1-50x50.png"),
        FeedItem("Nagarik News", "https://nagariknews.nagariknetwork.com/feed", "https://jscss.nagariknewscdn.com/images/nrm-new-logo.png?v=2025-10-05"),
        FeedItem("Rajdhani Daily", "https://rajdhanidaily.com/feed", "https://rajdhanidaily.com/wp-content/uploads/2022/04/rajdhani-author-small-120x120.png"),
        FeedItem("News of Nepal", "https://newsofnepal.com/feed", "https://newsofnepal.com/wp-content/uploads/2018/05/cropped-kamana-logo-2-32x32.png"),
        FeedItem("OS Nepal", "https://osnepal.com/feed", "https://www.osnepal.com/wp-content/uploads/2021/05/cropped-osnepal-logo-1-e1635184522403-32x32.png"),
        FeedItem("Techmandu", "https://techmandu.com/feed", "https://techmandu.com/wp-content/uploads/2020/11/cropped-techmandu-favicon-32x32.jpg"),
        FeedItem("Nepali Post", "https://nepalipost.com/beta/feed", "https://nepalipost.com/beta/wp-content/uploads/2018/02/logo-65x65.png"),
        FeedItem("Artha Sarokar", "https://arthasarokar.com/feed", "https://media.arthasarokar.com/uploads/2021/07/artha-sarokar-favicon-150x150.png"),
        FeedItem("Online TV Nepal", "https://onlinetvnepal.com/feed", "https://i0.wp.com/onlinetvnepal.com/wp-content/uploads/2025/05/cropped-favicon-copy.png?fit=32%2C32&#038;ssl=1"),
        FeedItem("Telegraph Nepal", "https://telegraphnepal.com/feed", "https://www.telegraphnepal.com/wp-content/uploads/2022/10/cropped-VRkw3Nkt_400x400-32x32.jpg"),
        FeedItem("The Kathmandu Post", "https://kathmandupost.com/rss", "https://jcss-cdn.kathmandupost.com/assets/images/logos/thekathmandupost-logo.png"),
        FeedItem("Karobar Daily", "https://karobardaily.com/feed", "https://www.karobardaily.com/wp-content/uploads/2024/01/cropped-karobardally-logo-32x32.png"),
        FeedItem("Baahrakhari", "https://baahrakhari.com/feed", "https://baahrakhari.com/themes/baahrakhari/images/logo.png"),
        FeedItem("Lokaantar", "https://lokaantar.com/feed", "https://lktcdn2.prixacdn.net/media/3bd6fbeb-e251-426c-9889-bd3881893610_klNg90Tp41.jpeg")
    )
}