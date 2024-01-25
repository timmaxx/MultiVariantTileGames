# Многовариантные плиточные игры
([En](./ReadMe.en.md))

Набор модулей и несколько вариантов сборок для создания двухмерных "плиточных" игр. <!-- описание репозитория -->

<!--Блок информации о репозитории в бейджах-->
![Static Badge](https://img.shields.io/badge/TimMax-MultiVariantTileGames-timmaxx)
![GitHub top language](https://img.shields.io/github/languages/top/timmaxx/MultiVariantTileGames)
![GitHub](https://img.shields.io/github/license/timmaxx/MultiVariantTileGames)
![GitHub Repo stars](https://img.shields.io/github/stars/timmaxx/MultiVariantTileGames)
![GitHub issues](https://img.shields.io/github/issues/timmaxx/MultiVariantTileGames)
<!--
![Logotype](./docs/wall.jpg)
-->

<!--
https://bulldogjob.com/readme/how-to-write-a-good-readme-for-your-github-project
-->


## 1. Введение
В мире существует большое множество типов игр, которые я называю "плиточные игры". И то, что есть общего у них всех, выделено или будет выделено в отдельные модули для дальнейшего использования при реализации конкретных плиточных игр. 

### 1.1. Что такое "плиточная игра"?
Такая игра всегда имеет прямоугольное поле, состоящее из N на M плиток, в каждой из плиток могут быть или не быть игровые объекты. Размер каждой плитки одинаков.
Взаимодействие объектов зависит в т.ч. от координат плиток - т.е. важно именно то, что тот или иной объект находится на той или иной плитке, у которой, в свою очередь, есть ближние и дальние соседи - другие объекты и/или плитки со всех её сторон (кроме боковых и угловых).
Ну и поэтому у каждой плитки есть свои уникальные двумерные координаты.

### 1.1.1. Несколько примеров "плиточных игр" (в строке, через запятую, совмещены по похожести в классификации):
- крестики-нолики, рендзю;
- шашки, шахматы, поддавки;
- уголки;
- го;
- сапёр;
- сокобан;
- нарды (только нужно представить это как доску 2 х 12);
- судоку, японский кроссворд;
- тетрис, змейка;
- морской бой.

### 1.1.2. Несколько примеров игр, не являющихся "плиточной игрой":
- лото;
- домино;
- карточные игры;
- ходилки (игровые объекты перемещаются по извилистым маршрутам).

### 1.2. Классификация плиточных игр. 
Имеющиеся игры были классифицированы ([см. сюда](./docs/ru/GameClassification.txt)). И эта классификация вполне может стать частью фреймворка.

## 2. Технологии
Языки:
- Java 18,
- HTML (пока не реализовано в клиенте),
- JavaScript (пока не реализовано в клиенте).

Системы сборки, модулирования, версионирования:
- Maven,
- git,
- Java 9 Modules (Project Jigsaw).

Метод сериализации и десериализации (для передачи между сервером и клиентами):
- Реализации интерфейса Externalizable.

Обмен информацией по сети:
- Java-WebSocket 1.5.4.

Логирование:
- SLF4J 1.7.36,
- LogBack 1.2.11.

GUI:
- Java-FX 18.0.1.

Тестирование:
- JUnit 5.0

## 3. Область применения функциональных возможностей

Данный проект является фреймворком. Для него можно разрабатывать и встраивать в него отдельные модули под конкретную плиточную игру, реализующие модель, визуализацию и управление игрой (модель-представление-контролер).
Как результат, могут быть получены различные варианты сборки. Так или иначе в функционирующем ПО будут сервер и клиент(ы). Приведу некоторые примеры таких вариантов:
- "Всё в одном" - сервер и клиент собраны в один исполняемый модуль, но доступны при этом только игры с одним игроком.
- Сервер. Может быть собран:
- - либо как целостное приложение,
- - либо как приложение, присоединяемое к Web-серверу.
- Клиент. Может быть собран:
- - либо как целостное приложение, собранное для старта на произвольной ОС (где есть JVM, в т.ч. Ms Windows, Linux).
- - - вероятно для смартфонов (Android, iOS) понадобятся специфические клиенты.
- - либо как браузерное приложение.

Данный проект является учебным.   
Возможно, он может оказаться полезным при освоении построений архитектур и тех технологий, которые в нём применяются.

## 3. Запуск (заготовка раздела)
Как запустить проект? Есть ли у проекта минимальные аппаратные требования?

Ранее мы уже упоминали о библиотеках и их версиях. При необходимости технологии, запуск и аппаратные требования можно объединить.
Но если разделить их на два подраздела, то здесь стоит остановиться именно на запуске проекта. Когда у нас есть сайт или приложение, речь может идти о настройке локального окружения, ссылке на страницы GitHub или развернутом приложении на Heroku. Нужны ли нам исходные данные? Если да, то в каком формате?

## 4. Примеры использования
В качестве примеров использования фреймворка представлены игры Сокобан и Сапёр.

## 5. Статус проекта
Проект находится в стадии разработки.

## 6. Источники (заготовка раздела)
Если наш код основан на чужом коде, то следует добавить такую информацию.
Если наш код был лишь вдохновлен другим решением/приложением, можно упомянуть об этом и написать, как вы вдохновились, какие изменения внесли, какой функционал развили.

## 7. Другая информация (заготовка раздела)
Информация об авторе, контактные данные, ссылки на www и социальные сети, тип лицензии, под которой предоставляется код, или информация о том, как внести свой вклад в проект, - это лишь примеры того, что можно добавить в ваш проект.

Автор: Тимченко Максим.

www: ???
(в т.ч. на котором можно разместить:
- видео с демонстрацией запуска и работы различных вариантов сборки,
- браузерный вариант клиента).

e-mail: 

## Иллюстрации
Иллюстрации стоит сделать. И в т.ч. отдельные лого для приложения и для github.
