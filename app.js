const app = (() => {
  const imgSmall = document.querySelector('.img-small')

  const loadImage = () => {
    const img = new Image()

    img.src = imgSmall.src
    img.addEventListener('load', (e) => {
      imgSmall.classList.add('loaded')
    }, false)

    const imgLarge = new Image()

    imgLarge.src = imgSmall.dataset.large
    imgLarge.addEventListener('load', (e) => {
      imgLarge.classList.add('loaded')
    }, false)

    imgSmall.parentNode.appendChild(imgLarge)
  }

  return {
    loadImage
  }

})()

app.loadImage()
