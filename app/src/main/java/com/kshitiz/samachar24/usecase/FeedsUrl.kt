package com.kshitiz.samachar24.usecase

import javax.annotation.concurrent.Immutable

@Immutable
data class FeedItem(val name: String, val url: String, val image: String, val isEnabled: Boolean = true)

object FeedsUrl {
    val nepaliFeeds = listOf(
//        FeedItem(
//            "Online Khabar",
//            "https://onlinekhabar.com/feed",
//            "https://www.onlinekhabar.com/wp-content/uploads/2017/05/logo-mobile1-50x50.png"
//        ),
        FeedItem(
            "Nagarik News",
            "https://nagariknetwork.com/feed/",
            "https://jscss.nagariknewscdn.com/images/nrm-new-logo.png?v=2025-10-05"
        ),
        FeedItem(
            "Rajdhani Daily",
            "https://rajdhanidaily.com/feed",
            "https://rajdhanidaily.com/wp-content/uploads/2022/04/rajdhani-author-small-120x120.png"
        ),
        FeedItem(
            "News of Nepal",
            "https://newsofnepal.com/feed",
            "https://newsofnepal.com/wp-content/themes/newsofnepalnaya/img/logo-newsofnepal.png"
        ),
//        FeedItem("OS Nepal", "https://osnepal.com/feed", "https://www.osnepal.com/wp-content/uploads/2021/05/cropped-osnepal-logo-1-e1635184522403-32x32.png"),
//        FeedItem("Techmandu", "https://techmandu.com/feed", "https://techmandu.com/wp-content/uploads/2020/11/cropped-techmandu-favicon-32x32.jpg"),
        FeedItem(
            "Eadarsha News",
            "https://www.eadarsha.com/feed",
            "https://i1.feedspot.com/5343403.jpg"
        ),
        FeedItem(
            "Nepali Post",
            "https://nepalipost.com/beta/feed",
            "https://nepalipost.com/beta/wp-content/uploads/2018/02/oie_422219GoBDAbYp.jpg"
        ),
        FeedItem(
            "Artha Sarokar",
            "https://arthasarokar.com/feed",
            "https://media.arthasarokar.com/uploads/2021/07/artha-sarokar-favicon-150x150.png"
        ),
        FeedItem(
            "Online TV Nepal",
            "https://onlinetvnepal.com/feed",
            "https://onlinetvnepal.com/wp-content/themes/onlinetv/img/on.png"
        ),
//        FeedItem(
//            "Telegraph Nepal",
//            "https://telegraphnepal.com/feed",
//            "https://www.telegraphnepal.com/wp-content/uploads/2022/10/cropped-VRkw3Nkt_400x400-32x32.jpg"
//        ),
//        FeedItem(
//            "The Kathmandu Post",
//            "https://kathmandupost.com/rss",
//            "https://jcss-cdn.kathmandupost.com/assets/images/logos/thekathmandupost-logo.png"
//        ),
        FeedItem(
            "Karobar Daily",
            "https://karobardaily.com/feed",
            "https://www.karobardaily.com/wp-content/themes/karobardaily/img/karobardally-logo.png"
        ),
//        FeedItem(
//            "Baahrakhari",
//            "https://baahrakhari.com/feed",
//            "https://baahrakhari.com/themes/baahrakhari/images/logo.png"
//        ),
        FeedItem(
            "Lokaantar",
            "https://lokaantar.com/rss/",
            "https://lktcdn2.prixacdn.net/media/3bd6fbeb-e251-426c-9889-bd3881893610_klNg90Tp41.jpeg"
        ),
        FeedItem(
            "NepalNews",
            "https://nepalnews.com/feed/",
            "https://nepalnews.com/wp-content/uploads/2025/08/Nepalnews.jpg"
        ),
        FeedItem(
            "Nepal Live",
            "https://nepallive.com/feed",
            "https://nepallive.com/themes/nepal-live/frontend/images/logo.png"
        ),
        FeedItem(
            "Arthapath",
            "https://www.arthapath.com/feed/",
            "https://www.arthapath.com/wp-content/uploads/2022/10/logo-arthabazar.png"
        ),
        FeedItem(
            "Bizpati",
            "https://bizpati.com/feed/",
            "https://bizpati.com/wp-content/themes/bizpati/img/logo.png"
        ),
        FeedItem(
            "Himal Press",
            "https://himalpress.com/feed/",
            "https://himalpress.com/wp-content/uploads/2022/11/icon-hp.jpg"
        ),
        FeedItem(
            "KendraBindu",
            "https://kendrabindu.com/feed/",
            "https://kendrabindu.com/wp-content/uploads/2024/01/logo.png"
        ),
//        FeedItem(
//            "Ukeraa",
//            "https://ukeraa.com/feed/",
//            "https://ukeraa.prixacdn.net/media/albums/ukeraa_NYPIabDGgv.jpeg"
//        ),
//        FeedItem(
//            "ThahaKhabar",
//            "https://thahakhabar.com/rss/",
//            "https://thahacdn.prixacdn.net/media/Logo_Thaha.jpg"
//        ),
        FeedItem(
            "Icon Khabar",
            "https://iconkhabar.com/feed",
            "https://iconkhabar.com/wp-content/uploads/2022/12/cropped-iconkhabar-3.png"
        ),
        FeedItem(
            "Abhiyan Daily",
            "https://www.abhiyandaily.com/rss/",
            "https://scontent.fbwa1-1.fna.fbcdn.net/v/t39.30808-6/305758041_451740456977957_3970610370243528066_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=6ee11a&_nc_ohc=BhFDt8frpAcQ7kNvwGBPl2A&_nc_oc=Admh2kOlh5nIoy80tUADxGf8zubzoAR6UL13HvzpJq-Y-dgsk7MuB6kmEJt1K5qUuOY&_nc_zt=23&_nc_ht=scontent.fbwa1-1.fna&_nc_gid=JYHbBqdHIewBanx7O68ylw&oh=00_AfdSVA0SFZUsp9_r0oSkBAyXoy6lYU3-bBDZyE95ocko0Q&oe=68F19427"
        ),
        FeedItem(
            "Arghakhanchi",
            "https://www.arghakhanchi.com/feed/",
            "https://yt3.ggpht.com/a/AATXAJymv7RvidhvQAQ6_xhJvki8iLZNtNihY_QUnQ=s900-c-k-c0xffffffff-no-rj-mo"
        ), FeedItem(
            "HamroKhelkud",
            "https://www.hamrokhelkud.com/feed",
            "https://www.hamrokhelkud.com/wp-content/uploads/2023/08/Branding-1-1-1-3.png"
        ), FeedItem(
            "Pariwartan Khabar",
            "https://www.pariwartankhabar.com/feed/",
            "https://www.pariwartankhabar.com/wp-content/themes/pariwartankhabar/img/logo2.png"
        ), FeedItem(
            "Ejanakpurtoday",
            "https://ejanakpurtoday.com/feed/",
            "https://ejanakpurtoday.com/wp-content/uploads/2021/10/cropped-logo_janakpurtoday-e1633643290527.png"
        ), FeedItem(
            "himalini",
            "https://www.himalini.com/feed/",
            "https://i1.wp.com/himalini.com/en/wp-content/uploads/2019/04/Himalini-logo-2-_.png?fit=221%2C210&ssl=1"
        ), FeedItem(
            "Nepaliheadlines",
            "https://nepaliheadlines.com/feed/",
            "https://nepaliheadlines.com/wp-content/uploads/2016/09/NH-logo.png"
        ), FeedItem(
            "Khasokhas",
            "https://www.khasokhas.com/feed",
            "https://www.khasokhas.com/wp-content/themes/khasokhas/img/logo.png"
        ), FeedItem(
            "Ukaalo",
            "https://www.ukaalo.com/rss/",
            "https://www.ukaalo.com/media/logo/Logo_ukalo1_ZyylU6I_1_ygea1iA_E6qwgMF.png"
        ), FeedItem(
            "Shikhar News",
            "https://shikharnews.com/feed",
            "https://ddxcu89oqzgqh.cloudfront.net/uploads/media/image/5c56d83857ba7858950086c4/shikhar-news-fb-share.jpg"
        )
    )
}