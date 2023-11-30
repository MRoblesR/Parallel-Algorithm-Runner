set dotenv-load
set dotenv-filename := 'vm.env'
current-time := `date +'%Y-%m-%d_%H-%M-%S'`

default: build upload_jar run download

build:
    mvn package

upload_jar: build
    ssh ${USER}@${ADDRESS} -i ${PRIVATE_KEY_PATH} 'mkdir Experiments/{{current-time}}'
    scp -B -i ${PRIVATE_KEY_PATH} ./target/jar-with-dependencies.jar ${USER}@${ADDRESS}:${REMOTE_PATH}Experiments/{{current-time}}/{{current-time}}.jar

run: upload_jar
    ssh ${USER}@${ADDRESS} -i ${PRIVATE_KEY_PATH} 'cd Experiments/{{current-time}}; nohup java -jar {{current-time}}.jar  ${Instances_folder} > logs.txt 2>&1'

download:
    ssh ${USER}@${ADDRESS} -i ${PRIVATE_KEY_PATH} 'cp -r Experiments Results; rm ./Results/*/*.jar'
    scp -B -i ${PRIVATE_KEY_PATH} -r ${USER}@${ADDRESS}:${REMOTE_PATH}/Results Results
    ssh ${USER}@${ADDRESS} -i ${PRIVATE_KEY_PATH} 'rm -r Results'

clean:
    mvn clean
