./mvnw clean install --batch-mode --show-version

if [ "$TRAVIS_PULL_REQUEST" == "false" ]; then
    if [ "$TRAVIS_BRANCH" == "master" ]; then
        ./mvnw versioneye:update --batch-mode
    else
        ./mvnw versioneye:securityAndLicenseCheck --batch-mode
    fi
fi
