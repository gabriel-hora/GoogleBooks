package com.example.googlebooks.repository

import com.example.googlebooks.model.ImageLinks
import com.example.googlebooks.model.Volume
import com.example.googlebooks.model.VolumeInfo

object BookVolumeMapper {
    fun bookToVolume(book: Book) =
        Volume(
            book.id,
            book.selfLink,
            VolumeInfo(
                book.title,
                book.description,
                book.authors,
                book.publisher,
                book.publisherDate,
                book.pageCount,
                ImageLinks(
                    book.smallThumbnail,
                    book.thumbnail
                )
            )
        )

    fun volumeToBook(volume: Volume) =
      volume.run {
          Book(
              id,
              selfLink,
              volumeInfo.title,
              volumeInfo.description,
              volumeInfo.authors,
              volumeInfo.publisher,
              volumeInfo.publisherDate,
              volumeInfo.pageCount,
              volumeInfo.imageLinks.smallThumbnail,
              volumeInfo.imageLinks.thumbnail
          )
      }
}