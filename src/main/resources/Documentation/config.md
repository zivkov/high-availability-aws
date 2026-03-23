@PLUGIN@ Configuration
=========================

Overview of all available configuration options:

### PubSub AWS provider configuration
```
[aws]
  region = us-east-1
  accessKeyIdLocation = etc/aws-access-key-id
  secretAccessKeyLocation = etc/aws-secret-access-key
[aws "topics"]
  main = default
  streamEvents = stream-events
[aws "limits"]
  messageProcessingThreadPoolSize = 4
  visibilityTimeout = 2s
  waitTime = 20s
  maxNumberOfMessages = 10
[aws "retry"]
  maxReceiveCount = 5
```

```pubsub.aws.region```
:   The AWS region where SNS/SQS is hosted. This setting is mandatory when
    using AWS as the PubSub provider.

```pubsub.aws.accessKeyIdLocation```
:   The location of the file containing the AWS access key ID. May be an
    absolute or relative path. If relative, it is resolved the site directory.
    This setting is mandatory when using AWS as the PubSub provider.

```pubsub.aws.secretAccessKeyLocation```
:   The location of the file containing the AWS secret access key. May be an
    absolute or relative path. If relative, it is resolved from the site directory.
    This setting is mandatory when using AWS as the PubSub provider.

```pubsub.topics.main```
:   The name of the main PubSub topic used for publishing events.
    Default: `default`

```pubsub.topics.streamEvents```
:   The name of the PubSub topic used for publishing stream events.
    Default: `stream-events`

```pubsub.aws.maxReceiveCount```
:   The maximum number of times a message can be received from the SQS queue
    before it is considered a failure and moved to the dead letter queue (if
    configured). Defaults to 5.

```pubsub.aws.messageProcessingThreadPoolSize```
:   The number of threads used to process messages received from SQS. Controls
    the concurrency of message processing. Defaults to 4.

```pubsub.aws.visibilityTimeout```
:   The duration that messages received from the SQS queue are hidden from other
    consumers. Messages will be redelivered to the queue if not deleted within
    this timeout, allowing for reprocessing in case of failures. During normal
    operation, messages should be deleted before this timeout expires.
    Value is expressed in Gerrit time values as in [websession.cleanupInterval](#websessioncleanupInterval).
    Defaults to 2 seconds.

```pubsub.aws.waitTime```
:   The duration that the SQS receiveMessage API will wait for messages to arrive
    in the queue before returning an empty response. This enables long polling,
    reducing the number of API calls and improving efficiency.
    Value is expressed in Gerrit time values as in [websession.cleanupInterval](#websessioncleanupInterval).
    Defaults to 20 seconds.

```pubsub.aws.maxNumberOfMessages```
:   The maximum number of messages to receive from the SQS queue in a single
    API call. Must be between 1 and 10. Increasing this value can reduce the
    number of API calls needed, but may increase the processing latency if
    not all messages are handled promptly.
    Defaults to 10.
