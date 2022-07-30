# Worker Queue


* Bu çalışma ile Türkiye'de bulunan şehirlerin hava durumu günlük olarak kaydedilecektir.

* producer uygulaması ile şehir adı ve tarih bilgisi, **weather_data_work.queue** isimle queue'ya gönderilir.

* consumer uygulaması ile **weather_data_work.queue** isimli queue'dan bu bilgiler 5'er 5'er okunur ve gerekli işlemler yapılır.

