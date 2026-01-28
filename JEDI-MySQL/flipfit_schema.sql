CREATE DATABASE IF NOT EXISTS FlipFitNew;
USE FlipFitNew;

-- -----------------------------------------------------
-- Table `GymOwner`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GymOwner` (
  `Id` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `panNumber` VARCHAR(50) NOT NULL,
  `cardDetails` VARCHAR(50) NOT NULL,
  `isApproved` TINYINT(1) DEFAULT 0,
  PRIMARY KEY (`Id`)
);

-- -----------------------------------------------------
-- Table `GymCentre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GymCentre` (
  `centreId` VARCHAR(255) NOT NULL,
  `ownerId` VARCHAR(255) NOT NULL,
  `centreName` VARCHAR(255) NOT NULL,
  `gstin` VARCHAR(50) NOT NULL,
  `city` VARCHAR(100) NOT NULL,
  `capacity` INT NOT NULL,
  `price` INT NOT NULL,
  `noOfSlots` INT NOT NULL DEFAULT 10,
  `openTime` INT NOT NULL DEFAULT 6,
  `isApproved` TINYINT(1) DEFAULT 0,
  PRIMARY KEY (`centreId`),
  FOREIGN KEY (`ownerId`) REFERENCES `GymOwner`(`Id`) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Table `Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Customer` (
  `Id` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  `cardDetails` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Id`)
);

-- -----------------------------------------------------
-- Table `Slot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Slot` (
  `slotId` VARCHAR(255) NOT NULL,
  `centreId` VARCHAR(255) NOT NULL,
  `time` TIME NOT NULL,
  PRIMARY KEY (`slotId`),
  FOREIGN KEY (`centreId`) REFERENCES `GymCentre`(`centreId`) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Table `Schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Schedule` (
  `scheduleId` VARCHAR(255) NOT NULL,
  `date` DATE NOT NULL,
  `slotId` VARCHAR(255) NOT NULL,
  `availability` INT NOT NULL,
  PRIMARY KEY (`scheduleId`),
  FOREIGN KEY (`slotId`) REFERENCES `Slot`(`slotId`) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Table `Booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Booking` (
  `bookingId` VARCHAR(255) NOT NULL,
  `userID` VARCHAR(255) NOT NULL,
  `scheduleID` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`bookingId`),
  FOREIGN KEY (`userID`) REFERENCES `Customer`(`Id`) ON DELETE CASCADE,
  FOREIGN KEY (`scheduleID`) REFERENCES `Schedule`(`scheduleId`) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Table `Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Admin` (
  `Id` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`Id`)
);
