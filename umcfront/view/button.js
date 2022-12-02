function boardCount() {
  const boards = document.querySelector(".boards");
  const counts = document.querySelector(".counts");
  const boardCount = boards.childElementCount;
  console.log(boardCount);
  counts.textContent = boardCount;
}

boardCount();
