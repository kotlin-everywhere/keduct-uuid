#!/bin/sh

echo "rootProject.name='keduct-uuid'\ninclude 'keduct-uuid-java', 'keduct-uuid-js'" > settings.gradle
touch build.gradle
keduct-uuid-java/gradlew wrapper
