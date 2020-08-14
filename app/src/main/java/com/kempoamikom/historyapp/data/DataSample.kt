package com.kempoamikom.historyapp.data

import com.kempoamikom.historyapp.entity.QuizEntity
import com.kempoamikom.historyapp.entity.FacilitiesEntity
import com.kempoamikom.historyapp.entity.SiteEntity

object DataSample {

    val descriptionLorem = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
    val levelEasy = "Mudah"
    val levelMedium = "Sedang"

    fun fetchFacilities(): List<FacilitiesEntity> {

        return listOf(
            FacilitiesEntity(
                "", "Martabak achahe ( Anna )",
                "Jl. Di Panjaitan No 55",
                "Simpang Kayu Agung Plaju Ilir",
                "data:image/jpeg;base64,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",
                "RestaurantModel(Martabak achahe ( Anna )",
                "", ""
            ),
            FacilitiesEntity(
                "", "Pempek Flamboyant",
                "Jl. DI Panjaitan-Plaju",
                "Simpang Kayu Agung Palembang Sumatera Selatan",
                "https://cf.shopee.co.id/file/020b5a64d63e49cbaf5834ad3b74f2d6",
                "Pempek Flamboyant merupakan Asian Restaurant ",
                "", ""
            ),
            FacilitiesEntity(
                "", "Solaria",
                "Jalan Gubernur Hasan A. Bastari ",
                "Lippo Plaza Jakabaring, Palembang Sumatera Selatan",
                "https://voa-islam.com/photos6/syafaat/Solaria.jpg",
                "Solaria merupakan Café yang berjarak 4.23 KM dari Plaju",
                "", ""
            ),
            FacilitiesEntity(
                "", "Ayam kalasan udin",
                "Panjaitan Plaju",
                "Plaju Palembang South sumatera",
                "https://truffle-assets.imgix.net/n0tuaih9ywav_6Plr9G3Kr6KkwSGi6AG4wW_ayam-goreng-kalasan_landscapeThumbnailClean_id.png",
                "Ayam kalasan udin merupakan Diner yang berjarak 0.79 KM dari Plaju",
                "", ""
            ),
            FacilitiesEntity(
                "", "Pempek Akiun",
                "DI. Panjaitan ",
                "Palembang Sumatera Selatan",
                "",
                "Pempek Akiun merupakan Snack Place yang berjarak 0.79 KM dari Plaju",
                "", ""
            )
//            RestaurantModel("", "",
//                "",
//                "",
//                "",
//                "")
            )
    }

    fun fetchSitus(): List<SiteEntity> {

        return listOf(
            SiteEntity(
                "", "Candi Prambanan",
                "",
                "",
                "Jl. Raya Solo - Yogyakarta No.16, Kranggan, Bokoharjo, Kec. Prambanan, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55571",
                "https://i1.wp.com/www.maioloo.com/maioloo/wp-content/uploads/2016/03/Candi-Prambanan-Yogyakarta-01.jpg.jpg?fit=1200%2C797&ssl=1",
                "", "",
                descriptionLorem
            ),
            SiteEntity(
                "", "Candi Borobudur",
                "",
                "",
                "Jl. Raya Solo - Yogyakarta No.16, Kranggan, Bokoharjo, Kec. Prambanan, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55571",
                "https://tfamanasek.com/wp-content/uploads/2019/01/Fasilitas-Candi-Borobudur.jpg",
                "", "",
                descriptionLorem
            ),
            SiteEntity(
                "",
                "Kampung Wisata Taman Sari",
                "",
                "",
                "Jl. Raya Solo - Yogyakarta No.16, Kranggan, Bokoharjo, Kec. Prambanan, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55571",
                "https://img.beritasatu.com/cache/beritasatu/600x350-2/1400469278.jpg",
                "", "",
                descriptionLorem
            ),
            SiteEntity(
                "", "Situs Warungboto",
                "",
                "",
                "",
                "Magelang",
                "", "",
                descriptionLorem
            ),
            SiteEntity(
                "",
                "Cagar Budaya",
                "",
                "",
                "Jl. Raya Solo - Yogyakarta No.16, Kranggan, Bokoharjo, Kec. Prambanan, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55571",
                "Magelang",
                "", "",
                descriptionLorem
            )
        )

    }

    fun fetchQuizEasy(): List<QuizEntity> {
        return listOf(
            QuizEntity(
                1, 1,
                "B",
                "Aceh Darussalam",
                "Siak",
                "Sriwijaya",
                "Samudera Pasai",
                levelEasy, "Berikut ini yang termasuk kerajaan Islam di Riau adalah"
            ),
            QuizEntity(
                2,
                2,
                "C",
                "Sultan Alauddin Riayat Syah at-Qahhar",
                "Sultan Hasanudin",
                "Sultan Mansyur Syah",
                "Sultan Mansyur Syah",
                levelEasy,
                "Kerajaan Kampar, Indragri, dan Siak dikuasai oleh kerajaan Malaka pada saat masa pemerintahan"
            ),
            QuizEntity(
                3, 3,
                "B",
                "Medan",
                "Aceh",
                "Makasar",
                "Jawa",
                levelEasy, "Hanzah Fansuri yaitu berasal dari "
            ),
            QuizEntity(
                4, 4,
                "C",
                "Sultan Mahmud Malik Zahir",
                "Sultan Malik as-Shaleh",
                "Sultan Suryanullah",
                "Abu Zain Malik Zahir",
                levelEasy, "Di bawah ini yang bukan raja dari kerajaan Samudera Pasai adalah"
            ),
            QuizEntity(
                5,
                5,
                "A",
                "Gujarat, Mesir, Persia, Arab",
                "Persia, Mesir, Gujarat, Cina",
                "Gujarat, Mesir, Cina, Arab",
                "Gujarat, Persia, Cina, Arab",
                levelEasy,
                "Berdasarkan pendapat para ahli, dapat disimpulkan agama Islam masuk ke Indonesia dibawa oleh para pedagang yang berasal dari"
            )
        )
    }

    fun fetchQuizMedium(): List<QuizEntity> {
        return listOf(
            QuizEntity(
                1, 1,
                "A",
                "Paramisora atau Iskandar Syah.",
                "Sultan Mansyur Syah",
                "Mudzafat Syah.",
                "Sultan Alaudin Syah.",
                levelMedium, "Kerajaan Malaka didirikan oleh"
            ),
            QuizEntity(
                2,
                2,
                "A",
                "Hoesein Djajadiningrat",
                "Snock Hurgronje dan Moquette",
                "Alwi Shihab",
                "Soetjipto Wirjosoeparto",
                levelMedium,
                "Ahli yang berpendapat bahwa agama Islam masuk ke Indonesia melalui Iran (Persia) yang dibuktikan dengan adanya “ejaan” dalam tulisan Arab, yaitu"
            ),
            QuizEntity(
                3, 3,
                "B",
                "Sultan Alauddin Riayat Syah at-Qahhar",
                "Sultan Alauddin Riayat Syah at-Qahhar",
                "Sultan Mudaffar Syah",
                "Sultan Ali Mughayat Syah",
                levelMedium, "Kerajaan aceh mengalami kemajian pada masa kesultanan"
            ),
            QuizEntity(
                4,
                4,
                "B",
                "Sriwijaya",
                "Barus",
                "Singasari",
                "Samudera Pasai",
                levelMedium,
                "Sultan Alauddin Riayat Syah sukses menaklukan banyak kerajaan setelah meminta bantuan dari negara Islam di timur tengah, salah satunya yaitu"
            ),
            QuizEntity(
                5,
                5,
                "A",
                "Sultan Iskandar Syah",
                "Sultan Mudzafar Syah",
                "Sultan Alauddin Syah",
                "Sultan Mansyur Syah",
                levelMedium,
                "Paramisora setelah menganut Islam mengukuhkan dirinya sebagai raja pertama Kerajaan Malaka dan mengubah namanya menjadi"
            )
        )
    }


}