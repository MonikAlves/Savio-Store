<div align="center">
  <img src="https://exbxwvxqlnbphyieygiz.supabase.co/storage/v1/object/public/Roupas/S-logo.png" width="100">
</div>

<h1 align="center"> Savio Store </h1>

<h1 align="center"> Links </h1>

<p align="center">
Site feito para a materia Programação Orientada a Objetos do curso Ciencias da Computação da UFG (Universidade Federal de Goiás)  <br/>
</p>

<p align="center">
  <a href="#-tecnologias">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-layout">Layout</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#%EF%B8%8F-estrutura-banco-de-dados">Estruturas banco de dados</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-autores">Autores</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
</p>


## 🚀 Tecnologias

Esse projeto foi desenvolvido com as seguintes tecnologias:

- React e JavaScript
- Java e Spring
- MySql
- JUnit e Mockito
- Git e Github

## 💻 Projeto

A Savio Store é um site destinado a compra de roupas e sapatos da marca Savio Store.


## 🔖 Layout
<div align="center">
  <table>
    <tr>
      <td>
        <img src="https://exbxwvxqlnbphyieygiz.supabase.co/storage/v1/object/public/Roupas/Captura%20de%20tela%202024-08-01%20142018%20-%20Copia.png" alt="Descrição da Imagem 1" width="500">
      </td>
      <td>
        <img src="https://exbxwvxqlnbphyieygiz.supabase.co/storage/v1/object/public/Roupas/Captura%20de%20tela%202024-08-01%20142047.png" alt="Descrição da Imagem 2" width="500">
      </td>
    <tr/>
    <tr>
      <td>
        <img src="https://exbxwvxqlnbphyieygiz.supabase.co/storage/v1/object/public/Roupas/Captura%20de%20tela%202024-08-01%20142030%20-%20Copia.png" alt="Descrição da Imagem 3" width="500">
      </td>
      <td>
        <img src="https://exbxwvxqlnbphyieygiz.supabase.co/storage/v1/object/public/Roupas/Captura%20de%20tela%202024-08-01%20142037.png" alt="Descrição da Imagem 4" width="500">
      </td>
    </tr>
    <tr>
      <td>
        <img src="https://exbxwvxqlnbphyieygiz.supabase.co/storage/v1/object/public/Roupas/Captura%20de%20tela%202024-08-01%20142131.png" alt="Descrição da Imagem 5" width="500">
      </td>
      <td>
        <img src="https://exbxwvxqlnbphyieygiz.supabase.co/storage/v1/object/public/Roupas/Captura%20de%20tela%202024-08-01%20142328.png" alt="Descrição da Imagem 6" width="500">
      </td>
    </tr>
  </table>
</div>

## 🗂️ Estrutura Banco de Dados

```mermaid
classDiagram
    class Client {
        -Long id
        -String name
        -String email
        -String phone
        -String password
        -String legalData
    }

    class Product {
        -Long id
        -String name
        -String description
        -String size
        -Double price
        -String stock
        -String image
    }

    class Cart {
        -Long id
        -Product product
        -Client client
        -Int quantity
        -String size
        -Boolean available
        -Double total
    }

    class Purchase {
        -Long id
        -Product product
        -Client client
        -Int quantity
        -String size
        -Double total
        -Date date
    }

    Cart --> Client
    Cart --> Product
    Purchase --> Client
    Purchase --> Product
```

## 💻 Autores


- [@Emmanuel](https://github.com/manotv-alt)
- [@Gabriel](https://github.com/GabSoares404)
- [@Luiz](https://github.com/LuizGDC7)
- [@Matheus](https://github.com/gauloish)
- [@Monik](https://github.com/MonikAlves)
