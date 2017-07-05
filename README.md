# noraui-datas-webservices

This REST Web service contains any sample for unit tests of NoraUi.

# Travis CI status

[![Build Status](https://travis-ci.org/NoraUi/noraui-datas-webservices.svg?branch=master)](https://travis-ci.org/NoraUi/noraui-datas-webservices)

# Technology

* Spring Boot
* Spring Web
* TestNG
* Angular 4
* Travis CI

# Run Anywhere
![RunAnywhere](/screenshots/plateforme.png)

Noraui Datas Web Services apps run anywhere the JVM does. Deploy standalone, in an app server, on a Cloud or all of the above.

# Production URL (Example)

use cases:
* http://localhost:8084/noraui/api/hello/columns return a list of column (for hello model) 

Errors cases:
* http://localhost:8084/noraui/api/fakemodel/columns return 204 No Content


# JSON response (Example)

{"columns":["author","zip","city","element","element2","date","title"],"rows":null}

# XML response (Example)

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?><dataModel><columns>author</columns><columns>zip</columns><columns>city</columns><columns>element</columns><columns>element2</columns><columns>date</columns><columns>title</columns></dataModel>
```

# License

[![license](https://img.shields.io/github/license/NoraUi/noraui-datas-webservices.svg)](https://github.com/NoraUi/noraui-datas-webservices/blob/master/LICENSE)
BSD, See LICENSE for details

# Contributing

The [issue tracker](https://github.com/NoraUi/noraui-datas-webservices/issues) is the preferred channel for bug reports, features requests and submitting pull requests.

For pull requests, editor preferences are available in the [editor config](.editorconfig) for easy use in common text editors. Read more and download plugins at <http://editorconfig.org>.


![footer](https://noraui.github.io/img/end.png)
