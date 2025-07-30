# challenge-vm-management-monitoring
Este repositório é destinado a armazenar o desafio técnico proposto em uma entrevista técnica


## módulo backend (API)
#### Foi utilizado Java 21 LTS com Springboot, Spring Web para gerenciar endpoints, Spring Secutiry para autenticação, banco de dados interno para armazenamento de dados H2, escolhido pela facilidade de não ter que configurar um banco de dados externo, o mesmo foi configurado para que armazene os dados em arquivo e não em memória, para manter os dados salvos mesmo após o reinicio da aplicação. A Arquitetura utilizada foi a MVC, comum e de fácil organização e entendimento de onde estão localizados os arquivos do projetos.

## APIS
#### POST: http://localhost:8080/auth/login -> para logar, é necessário utilizar o usuário ADM já cadastrado, basta enviar o payload baixo:

#### {
####    "email": "admin@claro.com",
####    "password": "admin"
#### }

#### O retorno será um token de autenticação e será necessário para conseguir acessar as demais APIS.
#### Para isso, nas demais APIS utilizando o postman, basta adiconar o token em Authorization -> Auth Type -> Bearer Token (e colar o token na área esquerda do painel)

#### POST: http://localhost:8080/user/create -> cria um novo usuário
#### PUT: http://localhost:8080/user/update-password -> atualiza o cadastro de usuários
#### GET: http://localhost:8080/user/all -> busca todos os usuários cadastrados
#### DELETE: http://localhost:8080/user/delete/{id} -> exclui um usuário com base no seu id de cadastro

#### POST: http://localhost:8080/VirtualMachine/create -> cria uma nova VM
#### PUT: http://localhost:8080/VirtualMachine/update -> atualiza uma VM, OBS: seu nome não pode ser alterado
#### {
#### "displayName": "vm3",
#### "cpu": 4,
#### "memory": 2048,
#### "status": "STOP"
#### }
#### GET: http://localhost:8080/VirtualMachine/all -> busca todas as VMs cadastradas
#### DELETE: http://localhost:8080/VirtualMachine/delete/{id} -> exclui uma VM por vez através do seu ID
