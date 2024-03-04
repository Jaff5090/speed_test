# Utilisez une image de base avec Java, Android SDK et autres outils nécessaires préinstallés
FROM openjdk:8

# Définissez l'environnement de travail
WORKDIR /app

# Installez Android SDK
ENV ANDROID_SDK_ROOT /usr/local/android-sdk
RUN mkdir $ANDROID_SDK_ROOT .android \
    && cd $ANDROID_SDK_ROOT \
    && wget -q https://dl.google.com/android/repository/commandlinetools-linux-6858069_latest.zip -O commandlinetools.zip \
    && unzip commandlinetools.zip -d cmdline-tools \
    && rm commandlinetools.zip

ENV PATH $PATH:$ANDROID_SDK_ROOT/cmdline-tools/tools/bin

# Acceptez les licences Android SDK avant d'installer des composants
RUN yes | sdkmanager --licenses

# Installez les composants spécifiques nécessaires pour votre projet
RUN sdkmanager "platform-tools" "platforms;android-30" "build-tools;30.0.3"

# Copiez votre projet Android dans le conteneur
COPY . .

# Compilez votre projet (changez selon votre système de build, par exemple, Gradle)
RUN ./gradlew build
