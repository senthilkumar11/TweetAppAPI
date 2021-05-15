	aws dynamodb create-table \
    --table-name roles \
    --attribute-definitions \
        AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=5\
	--endpoint-url http://localhost:9090
	
	sleep 5
	
	aws dynamodb create-table \
    --table-name users \
    --attribute-definitions \
        AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=5\
	--endpoint-url http://localhost:9090
	
	sleep 5
	
	aws dynamodb create-table \
    --table-name Tweets \
    --attribute-definitions \
        AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=5\
	--endpoint-url http://localhost:9090
	sleep 5
	
	aws dynamodb create-table \
    --table-name reply \
    --attribute-definitions \
        AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=5\
	--endpoint-url http://localhost:9090
	sleep 5
	