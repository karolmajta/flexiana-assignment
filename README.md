# Flexiana assignment

## Prerequisites

This projects uses a standard clojure/cloujurescript/leiningen setup, so to run it
from fresh checkout you'll need `lein` on your path, and Java JDK on your system.

You can get the latest Java JDK at https://www.oracle.com/technetwork/java/javase/downloads/index.html

Instructions on how to set up leiningen can be found at https://leiningen.org/

## Running the tests

In the checkout directory issue:

```
$ lein test
```

## Running the backend/API

In the checkout directory issue:

```
$ lein ring server
```

This will start a http server listening on localhost:3000 serving the API
and open a web browser.

## Running the frontend

In the checkout directory (in new terminal tab/window) issue:

```
$ lein figwheel
```

This will start a http server listening on 0.0.0.0:3449 serving the
frontend application in development mode.
