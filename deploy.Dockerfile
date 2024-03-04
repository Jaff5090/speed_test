# Utilisez une image de base avec Java
FROM openjdk:8

# Définissez l'environnement de travail
WORKDIR /app

# Téléchargez et installez Android Commandline Tools
RUN wget -q https://dl.google.com/android/repository/commandlinetools-linux-6858069_latest.zip -O commandlinetools.zip && \
    unzip commandlinetools.zip -d ${ANDROID_SDK_ROOT}/cmdline-tools && \
    rm commandlinetools.zip

# Définissez le PATH pour inclure le SDK Android et les outils de ligne de commande
ENV ANDROID_HOME=${ANDROID_SDK_ROOT}
ENV PATH=${PATH}:${ANDROID_HOME}/cmdline-tools/latest/bin:${ANDROID_HOME}/platform-tools

# Acceptez les licences Android SDK avant d'installer des composants
RUN yes | sdkmanager --licenses

# Installez les composants spécifiques nécessaires pour votre projet
# Remplacez "platforms;android-30" et "build-tools;30.0.3" par les versions que vous utilisez
RUN sdkmanager "platform-tools" "platforms;android-30" "build-tools;30.0.3"

# Copiez votre projet Android dans le conteneur
COPY . .

# Compilez votre projet (changez selon votre système de build, par exemple, Gradle)
RUN ./gradlew build
