<!--
  Copyright 2016 GantSign Ltd. All Rights Reserved.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
# Error Prone Checks

[![Build Status](https://travis-ci.org/gantsign/error-prone-checks.svg?branch=master)](https://travis-ci.org/gantsign/error-prone-checks)
[![codecov](https://codecov.io/gh/gantsign/error-prone-checks/branch/master/graph/badge.svg)](https://codecov.io/gh/gantsign/error-prone-checks)
[![Dependency Status](https://www.versioneye.com/user/projects/57e156d23adc05001270eb60/badge.svg?style=flat-round)](https://www.versioneye.com/user/projects/57e156d23adc05001270eb60)
[![SonarQube Tech Debt](https://img.shields.io/sonar/https/sonarqube.com/com.github.gantsign.errorprone:error-prone-checks/tech_debt.svg)](https://sonarqube.com/overview?id=com.github.gantsign.errorprone%3Aerror-prone-checks)
[![License](https://img.shields.io/badge/license-apache-blue.svg)](LICENSE)

Additional checks for use with [Google Error Prone](http://errorprone.info).

# About

The GantSign Error Prone Checks pickup where
[Google Error Prone](http://errorprone.info/) and our
[Checkstyle configuration](https://github.com/gantsign/checkstyle-config) leaves
off.

Google Error Prone focuses on likely bugs rather than poor style and outdated
APIs; [Checkstyle](http://checkstyle.sourceforge.net) works on the source code
and lacks the information about types to be able to write rules banning
APIs that are deprecated / superseded / error prone.

The GantSign Error Prone checks fill this gap to push developers to use the best
APIs currently available.

# License

Code is under the
[Apache Licence v2](https://www.apache.org/licenses/LICENSE-2.0.txt).

# Author Information

John Freeman

GantSign Ltd.
Company No. 06109112 (registered in England)
