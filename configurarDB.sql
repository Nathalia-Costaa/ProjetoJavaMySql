-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Premio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Premio` (
  `idPremio` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPremio`));


-- -----------------------------------------------------
-- Table `mydb`.`Ator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Ator` (
  `idAtor` INT NOT NULL AUTO_INCREMENT,
  `nome_ator` VARCHAR(45) NOT NULL,
  `data_nascimento_ator` VARCHAR(45) NOT NULL,
  `idPremio` INT NULL,
  PRIMARY KEY (`idAtor`),
  CONSTRAINT `fk_Ator_Premio`
    FOREIGN KEY (`idPremio`)
    REFERENCES `mydb`.`Premio` (`idPremio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Categoria` (
  `idCategoria` INT NOT NULL AUTO_INCREMENT,
  `nome_categoria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCategoria`));


-- -----------------------------------------------------
-- Table `mydb`.`Filme`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Filme` (
  `idFilme` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NOT NULL,
  `idCategoria` INT NOT NULL,
  PRIMARY KEY (`idFilme`),
  CONSTRAINT `fk_Filme_Categoria1`
    FOREIGN KEY (`idCategoria`)
    REFERENCES `mydb`.`Categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`RelacaoAtorFilme`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`RelacaoAtorFilme` (
  `idAtor` INT NOT NULL,
  `idFilme` INT NOT NULL,
  PRIMARY KEY (`idAtor`, `idFilme`),
  CONSTRAINT `fk_Ator_has_Filme_Ator1`
    FOREIGN KEY (`idAtor`)
    REFERENCES `mydb`.`Ator` (`idAtor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ator_has_Filme_Filme1`
    FOREIGN KEY (`idFilme`)
    REFERENCES `mydb`.`Filme` (`idFilme`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO `mydb`.`categoria` (`nome_categoria`) VALUES ('romance');
INSERT INTO `mydb`.`categoria` (`nome_categoria`) VALUES ('comedia');
