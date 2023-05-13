#!/usr/bin/env bash
#shellcheck disable=SC2034

set -eu -o pipefail

# Tag to be used when creating the Docker image.
TAG="$(git rev-parse --short HEAD)"

# Branch on which the current image is being build.
BRANCH="$(git rev-parse --abbrev-ref HEAD)"

# The application will be deployed only when commits belong to this branch
DEPLOYMENT_BRANCH="main"

# Docker repository in which the application image will be pushed
DOCKER_IMAGE_REPOSITORY="736523801992.dkr.ecr.eu-west-2.amazonaws.com/hop-tutorial"
