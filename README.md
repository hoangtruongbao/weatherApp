# weatherApp
Simple Android weather app using MVVM clean architecture.
# Build the application
1. Get the source code https://github.com/hoangtruongbao/weatherApp.git
2. Configure the project
   The weather information provided in this application belongs to  https://openweathermap.org/
, so you need an API Key from their platform in order to display it. You can get one from this  https://openweathermap.org/.

Once you have it, open APIHelper in data -> base

const val APP_ID = YOUR API HERE
Replace "YOUR API KEY HERE" with your API Key from openweathermap.

#Technologies & Methodologies which used:
Koin
Coroutines
Clean Architecture
MVVM Pattern
LiveData
