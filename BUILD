load(
    "@com_googlesource_gerrit_bazlets//:gerrit_plugin.bzl",
    "gerrit_plugin",
    "gerrit_plugin_dependency_tests",
    "gerrit_plugin_test_util",
    "gerrit_plugin_tests",
)
load(
    "@com_googlesource_gerrit_bazlets//tools:in_gerrit_tree.bzl",
    "in_gerrit_tree_enabled",
)
load(
    "@com_googlesource_gerrit_bazlets//tools:runtime_jars_allowlist.bzl",
    "runtime_jars_allowlist_test",
)
load(
    "@com_googlesource_gerrit_bazlets//tools:runtime_jars_overlap.bzl",
    "runtime_jars_overlap_test",
)
load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "high-availability-plugin-neverlink",
    neverlink = True,
    exports = ["//plugins/high-availability"],
)

PLUGIN = "high-availability-aws"

PLUGIN_DEPS = [
    "software.amazon.awssdk:sns",
    "software.amazon.awssdk:auth",
    "software.amazon.awssdk:http.client.spi",
    "software.amazon.awssdk:regions",
    "software.amazon.awssdk:sqs",
    "software.amazon.awssdk:netty.nio.client",
    "software.amazon.awssdk:aws_crt.client",
]

gerrit_plugin(
    srcs = glob(["src/main/java/**/*.java"]),
    ext_deps = PLUGIN_DEPS,
    license = "LICENSE",
    manifest_entries = [
        "Gerrit-PluginName: high-availability-aws",
        "Gerrit-Module: com.googlesource.gerrit.plugins.highavailability.aws.Module",
        "Implementation-Title: high-availability aws sns+sqs provider",
        "Implementation-URL: https://gerrit-review.googlesource.com/#/admin/projects/plugins/high-availability-aws",
    ],
    plugin = PLUGIN,
    resources = glob(["src/main/resources/**/*"]),
    deps = ["high-availability-plugin-neverlink"],
)

PLUGIN_TEST_DEPS = PLUGIN_DEPS + [
    "org.testcontainers:testcontainers",
    "org.testcontainers:localstack",
]

gerrit_plugin_tests(
    name = "tests",
    srcs = glob(["src/test/java/**/*.java"]),
    ext_deps = PLUGIN_TEST_DEPS,
    javacopts = ["-Xep:DoNotMock:OFF"],
    plugin = PLUGIN,
    resources = glob(["src/test/resources/**/*"]),
    tags = [
        PLUGIN,
        "local",
    ],
    deps = ["//plugins/high-availability"],
)

#gerrit_plugin_dependency_tests(plugin = "high-availability-aws")
