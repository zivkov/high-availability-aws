load("@rules_java//java:defs.bzl", "java_library")
load("//tools/bzl:junit.bzl", "junit_tests")
load(
    "//tools/bzl:plugin.bzl",
    "PLUGIN_DEPS",
    "PLUGIN_TEST_DEPS",
    "gerrit_plugin",
)

gerrit_plugin(
    name = "high-availability-aws",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: high-availability-aws",
        "Gerrit-Module: com.googlesource.gerrit.plugins.highavailability.aws.Module",
        "Implementation-Title: high-availability aws sns+sqs provider",
        "Implementation-URL: https://gerrit-review.googlesource.com/#/admin/projects/plugins/high-availability-aws",
    ],
    resources = glob(["src/main/resources/**/*"]),
    deps = [
        "high-availability-plugin",
        "aws-client",
    ],
)

java_library(
    name = "high-availability-plugin",
    neverlink = True,
    exports = ["//plugins/high-availability"],
)

java_library(
    name = "global-refdb-neverlink",
    neverlink = 1,
    exports = ["//plugins/global-refdb"],
)

AWS_CLIENT_LIBS = [
    "@aws_sdk_sns//jar",
    "@aws_sdk_sqs//jar",
    "@aws_sdk_netty//jar",
    "@aws_auth//jar",
    "@aws_regions//jar",
    "@aws_identity_spi//jar",
    "@aws_utils//jar",
    "@aws_http_client_spi//jar",
    "@aws_sns//jar",
    "@aws_core//jar",
    "@aws_sdk_core//jar",
    "@aws_retries_spi//jar",
    "@aws_profiles//jar",
    "@aws_endpoints_spi//jar",
    "@aws_http_auth_api//jar",
    "@netty-common//jar",
    "@netty-transport//jar",
    "@aws_http_auth_aws//jar",
    "@aws_http_auth//jar",
    "@reactive-streams//jar",
    "@aws_retries//jar",
    "@netty-handler//jar",
    "@netty-buffer//jar",
    "@aws_metrics_spi//jar",
    "@aws_query_protocol//jar",
    "@aws_protocol_core//jar",
    "@aws_checksums//jar",
    "@aws_checksums_spi//jar",
    "@netty-codec-http2//jar",
    "@netty-codec-http//jar",
    "@netty-codec//jar",
    "@netty-resolver//jar",
    "@aws_json_protocol//jar",
    "@aws_json_utils//jar",
    "@aws_third_party_jackson_core//jar",
    "@aws_crt_client//jar",
    "@aws_crt//jar",
    "@aws_crt_core//jar",
    "@netty_transport_native_unix_common//jar",
]

java_library(
  name = "aws-client",
  exports = AWS_CLIENT_LIBS,
)

java_library(
    name = "high-availability-aws__plugin_test_deps",
    testonly = 1,
    visibility = ["//visibility:public"],
    exports = PLUGIN_DEPS + PLUGIN_TEST_DEPS + [
        #"high-availability-plugin",
        "//plugins/high-availability",
        "//plugins/global-refdb",
        "high-availability-aws__plugin",
        "aws-client",
        "@docker-java-api//jar",
        "@docker-java-transport-zerodep//jar",
        "@docker-java-transport//jar",
        "@duct-tape//jar",
        "@jackson-annotations//jar",
        "@testcontainers//jar",
        "@testcontainers-aws-localstack//jar",

    ],
)

java_library(
    name = "test_util",
    testonly = True,
    srcs = glob(
        ["src/test/java/**/*.java"],
        exclude = ["src/test/java/**/*Test.java"],
    ),
    deps = [
        "high-availability-aws__plugin_test_deps",
    ]
)

junit_tests(
    name = "high-availability-aws_tests",
    srcs = glob(["src/test/java/**/*Test.java", "src/test/java/**/*IT.java"]),
    javacopts = ["-Xep:DoNotMock:OFF"],
    resources = glob(["src/test/resources/**/*"]),
    tags = [
        "high-availability-aws",
        "local",
    ],
    deps = PLUGIN_DEPS + PLUGIN_TEST_DEPS + [
        "test_util",
        "high-availability-aws__plugin_test_deps",
    ],
)
