# High Availability for Amazon AWS

This plugin extends the Gerrit High Availability plugin by using Amazon AWS SNS
(Simple Notification Service) and SQS (Simple Queue Service) as the message
broker for forwarding events between Gerrit instances instead of HTTP-based
direct communication.

## Amazon SNS/SQS as Message Broker

Amazon SNS/SQS provides:

* **Decoupling**: Instances do not need to know about each other directly or
  maintain HTTP connections
* **Scalability**: Automatically scales to handle high message volume
* **Reliability**: Messages are persisted and retried automatically with
  exponential backoff
* **Dead-letter queues**: Messages that exceed max receive count are
  automatically moved to a dead-letter queue for debugging

## Prerequisites

* AWS Account with SNS and SQS services enabled
* AWS IAM user or role with the following permissions:
  - `sns:CreateTopic`, `sns:PublishMessage`
  - `sqs:CreateQueue`, `sqs:SendMessage`, `sqs:ReceiveMessage`, `sqs:DeleteMessage`
  - `sqs:SetQueueAttributes` (for dead-letter queue configuration)

## How It Works

Instead of the base plugin's HTTP-based peer communication model, this AWS
extension:

1. Publishes all HA events (cache evictions, index updates, stream events) to
   an SNS topic
2. Each Gerrit instance subscribes via its own SQS queue
3. Messages are processed asynchronously by each instance's queue handler
4. Failed messages are automatically retried per SQS retry policy
5. Messages exceeding the max receive count are moved to a dead-letter queue

## Dead-Letter Queue Handling

Messages that fail processing or exceed the maximum receive count are
automatically moved to the dead-letter queue. Use AWS Console or AWS CLI to:

* Inspect failed messages for troubleshooting
* Replay messages from the dead-letter queue

## Monitoring and Debugging

Monitor message flow via CloudWatch metrics or AWS Console:

* Queue depth (messages pending)
* Messages sent/received
* Dead-letter queue activity
* Processing latency

For troubleshooting steps, refer to [config.md](config.md) and
[AWS SNS/SQS documentation](https://docs.aws.amazon.com/sns/).
