#!/usr/bin/env bash

set -euo pipefail

awslocal dynamodb --region us-east-1 create-table \
   --table-name tf-customers-table \
   --attribute-definitions AttributeName=id,AttributeType=S \
                           AttributeName=cpf,AttributeType=S \
   --key-schema AttributeName=id,KeyType=HASH \
   --provisioned-throughput ReadCapacityUnits=2,WriteCapacityUnits=2 \
    --global-secondary-index "[
        {
      \"IndexName\": \"cpf\",
      \"KeySchema\": [
        {
           \"AttributeName\": \"cpf\",
          \"KeyType\": \"HASH\"
        }
      ],
      \"Projection\": {
        \"ProjectionType\": \"ALL\"
      },
      \"ProvisionedThroughput\": {
        \"ReadCapacityUnits\": 1,
        \"WriteCapacityUnits\": 1
      }
    }
    ]"

awslocal sqs create-queue --region us-east-1 --queue-name notification-queue
awslocal sqs create-queue --region us-east-1 --queue-name order-queue
awslocal sqs create-queue --region us-east-1 --queue-name payment-queue