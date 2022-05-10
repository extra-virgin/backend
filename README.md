# TinkoffTradingRobot

[RU]
Описание.

Бэкенд принимает запрос с токеном авторизации, идентификатором торговой стратегии и списком figi/тикеров фьючерсов, которые предполагаются к торговле.
Эти данные привязываются к конкретному пользователю, идентифицируемому по accoundID, и сохраняются в базу данных. 

MonitoringService занимается наблюдением за сконфигурированным пользователем списком инструментов, каждый из которых однозначно идентифицируется по figi.
По gRPC он непрерывно получает данные из Тинькофф-сервисов, и согласно пользовательской настройке передает их в один из наследников SolutionMaker -- эти сервисы ответственны за принятие решений о покупке или продаже инструментов. На данный момент доступен только StubSolutionMaker, который случайно, с шансом 0.1, покупает инструменты и продает их по стоп-лоссу с убытком в 0.5% или по тейк-профиту с прибылью в 1%. Также при совершении сделок StubSolutionMaker учитывает и сверяет с настройками текущий капитал пользователя и объем свободной валюты типа, необходимого для сделки по конкретному финансовому инструменту.
Кроме настроенных пользователем figi, ведется наблюдение за figi инструментов по которым открыты позиции -- это необходимо чтобы при смене конфигурации торговый робот не оставил открытые инвестиционные позиции.

По запросу бэкенд располагает возможностью вернуть список совершенных сделок с подробной информацией о них.

---

## Database localhost:

To run database on local postgresql specify user in spring.application.

---

## API:

> Не хочу думать над методами типа Create, Delete, Update, так что если 
> что пусть будет псевдоRest.

Важно: 

1) Для любого запроса  требуется наличия в Header запроса пары `X-Authorization: token`, иначе будет прилетать 403 
или для `/api/user/create` 400.

2) Первая таблица - всегда запрос, вторая таблица - всегда ответ.

3) Если таблица пустая, то это значит, что она еще не заполнена. Если таблица с черточками - значит она пустая.


---

### `/api/user`

Взаимодействие с получением доступа для пользователя: добавление, удаление токена.


#### `[POST] /create`

Описание: добавление токена и, таким образом, получение доступа к основному функционалу.

RequestBody:

| Field | Type | Description |
|-------|------|-------------|
| -     | -    | -           |

ResponseBody:

| Field | Type | Description |
|-------|------|-------------|
| -     | -    | -           |


#### `[POST] /delete`

Описание:  удаление токена и связанных с ним `accounts` и `orders`.

RequestBody:

| Field | Type | Description |
|-------|------|-------------|
| -     | -    | -           |

ResponseBody:

| Field | Type | Description |
|-------|------|-------------|
| -     | -    | -           |


---

### `/api/account`

Взаимодействие с аккаунтами пользователей: добавление, удаление, а также изменение правил алгоритмов.


#### `[GET]`

Описание: получения всех аккаунтов, привязанных к текущему токену взаимодействия.

RequestBody:

| Field | Type | Description |
|-------|------|-------------|
|       |      |             |

ResponseBody:

| Field    | Type                      | Description                   |
|----------|---------------------------|-------------------------------|
| accounts | Массив объектов `Account` | Список аккаунтов пользователя |


#### `[POST] /create`

Описание: добавление счета пользователя.

RequestBody:

| Field    | Type     | Description                                    |
|----------|----------|------------------------------------------------|
| id       | string   | Уникальный идентификатор аккаунта пользователя |
| strategy | Strategy | Стратегия торгового робота                     |

ResponseBody:

| Field | Type | Description |
|-------|------|-------------|
|       |      |             |


#### `[POST] /delete`

Описание: удаление счета пользователя и связанных с ним заявок.

RequestBody:

| Field | Type   | Description                                    |
|-------|--------|------------------------------------------------|
| id    | string | Уникальный идентификатор аккаунта пользователя |

ResponseBody:

| Field | Type | Description |
|-------|------|-------------|
|       |      |             |


#### `[POST] /update`

Описание: изменение существующего счета пользователя.

RequestBody:

| Field    | Type     | Description                                    |
|----------|----------|------------------------------------------------|
| id       | string   | Уникальный идентификатор аккаунта пользователя |
| strategy | Strategy | Стратегия торгового робота                     |

ResponseBody:

| Field | Type | Description |
|-------|------|-------------|
|       |      |             |


---

### `/api/order` 

Взаимодействие с заявками пользователя


---

### Api Objects

#### Strategy

| Name | Number | Description              |
|------|--------|--------------------------|
| NONE | 0      | Торговый робот неактивен |
| STUB | 1      |                          |


#### Account

| Field | Type | Description |
|-------|------|-------------|
|       |      |             |

