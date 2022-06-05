## Task
Необходимо разработать сервис социальных связей.  
На входе имеется журнал коммуникаций между пользователями. Необходимо строить граф социальных связей по этому журналу.  

Это должен быть HTTP REST сервис, в нотации json.  
Необходимы следующие методы:  
    - добавление записи факта коммуникации  
    - получение графа социальных связей, при этом граф должен содержать не только структуру по узлам и ребрам, но и иметь раздел со сводной информацией по количествам коммуникаций между пользователями (минимальное, максимальное, среднее)  

Для хранения журнала необходимо использовать СУБД PostgreSQL (или другая СУБД).  

Результат должен быть оформлен как репозиторий в git, сопровожден краткой инструкцией по установке и настройке.
## Setup
Run postgresql and login as privileged user   

create database social_links;

grant all on database social_links to _your_database_user_name_;

create sequence user_interaction_id_increment_seq  
increment 1  
start 1;  

create table user_interaction (  
id bigint primary key,  
initiator_user_id bigint not null,  
acceptor_user_id bigint not null  
);  

In application.properties file set
 - server port
 - database's _url, username and password_  
 - logging levels and path to logs  

Run main method of SocialLinksApplication class or deploy social-links.jar
## Usage
To add user interaction event send POST request to   
_your_server_address_:_your_port_/userInteraction/createUserInteraction  
with parameters
 - initiatorUserId
 - acceptorUserId  
  
To get user interactions graph send GET request to
_your_server_address_:_your_port_/userInteraction/getUserInteractionGraph  
In response one gets response in JSON form, for example:  
```
{
    "1": [
        {
            "interactionNum": 23,
            "acceptorUserId": 4
        },
        {
            "interactionNum": 5,
            "acceptorUserId": 67
        }
    ]
}
```
means user with id = 1 had 23 interactions with user with id = 4  
and 5 interactions with user with id = 67