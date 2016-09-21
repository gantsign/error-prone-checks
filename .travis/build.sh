#!/bin/bash

./mvnw clean install --batch-mode --show-version || exit 1

if [ "$TRAVIS_PULL_REQUEST" == "false" ]; then
    if [ "$TRAVIS_BRANCH" == "master" ]; then
        # VersionEye update
        timeout --kill-after=30s 2m \
            ./mvnw versioneye:update --batch-mode
        exit_code=$?
        if [ "$exit_code" == '124' ] || [ "$exit_code" == '137' ]; then
            echo 'Timed out performing versioneye:update'
        else
            if [ "$exit_code" != '0' ]; then
                exit $exit_code
            fi
        fi

        # SonarQube update
        ./mvnw sonar:sonar -Dsonar.login=$SONAR_TOKEN -Dsonar.host.url=https://sonarqube.com
    else
        # VersionEye check
        timeout --kill-after=30s 2m \
            ./mvnw versioneye:securityAndLicenseCheck --batch-mode
        exit_code=$?
        if [ "$exit_code" == '124' ] || [ "$exit_code" == '137' ]; then
            echo 'Timed out performing versioneye:securityAndLicenseCheck'
        else
            if [ "$exit_code" != '0' ]; then
                exit $exit_code
            fi
        fi
    fi
fi
