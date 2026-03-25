# Amazwon AWS pubsub messaging for the Gerrit's high-availability plugin

This plugin provides an extension of the Gerrit's high-availability plugin:
it provides AWS SNS+SQS based message forwarding.

## License

This plugin is released under the same Apache 2.0 license and copyright holders
as of the Gerrit Code Review project.

## How to build

Refer to the [build instructions in the plugin documentation](src/main/resources/Documentation/build.md).

## Installation

Install

- the `high-availability-aws` plugin in the Gerrit site's `plugins` folder.

## Sample configuration

This is a minimal configuration:
```
[aws]
  region = eu-central-1
  accessKeyIdLocation = aws/accessKeyId
  secretAccessKeyLocation = aws/secretAccessKey
```

A complete set of configuration options is given in the following example.
The example values given here are also the default values:
```
[aws]
  region = eu-central-1
  accessKeyIdLocation = aws/accessKeyId
  secretAccessKeyLocation = aws/secretAccessKey
[aws "topics"]
  main = default
  streamEvents = stream-events
[aws "limits"]
  visibilityTimeout = 2s
  waitTime = 20s
  maxNumberOfMessages = 10
  messageProcessingThreadPoolSize = 4
[aws "retry"]
  maxReceiveCount = 5
```
