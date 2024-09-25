CREATE DATABASE dundermifflin;

USE dundermifflin;

CREATE TABLE Setor (
    codigo_setor INT PRIMARY KEY AUTO_INCREMENT,
    nome_setor VARCHAR(255) NOT NULL
);

CREATE TABLE Funcionario (
    codigo_funcionario INT PRIMARY KEY AUTO_INCREMENT,
    nome_funcionario VARCHAR(255) NOT NULL,
    cargo_funcionario VARCHAR(255),
    urlImagem_funcionario VARCHAR(255),
    setor_codigo INT,
    FOREIGN KEY (setor_codigo) REFERENCES Setor(codigo_setor) ON DELETE SET NULL
);

CREATE TABLE Administrador (
    codigo_administrador INT PRIMARY KEY,
    email_administrador VARCHAR(255) NOT NULL,
    senha_administrador VARCHAR(255) NOT NULL,
    FOREIGN KEY (codigo_administrador) REFERENCES Funcionario(codigo_funcionario) ON DELETE CASCADE
);

CREATE TABLE Relatorio (
    id INT PRIMARY KEY AUTO_INCREMENT,
    problema VARCHAR(255) NOT NULL,
    data DATE NOT NULL,
    setor_codigo INT,
    funcionario_codigo INT,
    FOREIGN KEY (setor_codigo) REFERENCES Setor(codigo_setor) ON DELETE CASCADE,
    FOREIGN KEY (funcionario_codigo) REFERENCES Funcionario(codigo_funcionario) ON DELETE SET NULL
);
