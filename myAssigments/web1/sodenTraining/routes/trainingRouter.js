const express = require("express");
const trainingRouter = express.Router();

trainingRouter
  .route("/")
  .get((req, res, next) => {})
  .post((req, res, next) => {})
  .put((req, res, next) => {})
  .delete((req, res, next) => {});

trainingRouter.route("/book").get((req, res, next) => {
  res.render("book.ejs", { title: "Book a session" });
  console.log("Routed to booking page");
});

trainingRouter.route("/manage").get((req, res, next) => {
  res.render("managing.ejs", { title: "Manage your session" });
  console.log("Routed to manage booking page");
});

trainingRouter.route("/contact").get((req, res, next) => {
  res.render("contact.ejs", { title: "Contact Us" });
  console.log("Routed to contact us page");
});

trainingRouter.route("/help").get((req, res, next) => {
  res.render("help.ejs", { title: "Help/Faqs" });
  console.log("Routed to contact us page");
});

module.exports = trainingRouter;
