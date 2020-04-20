package fr.rafoufoun.data.model

import androidx.room.*

@Entity(tableName = "review")
data class ReviewDB(
    @PrimaryKey val name: String
)

@Entity(
    tableName = "section",
    primaryKeys = ["reviewName", "label"]
)
data class SectionDB(
    @ForeignKey(entity = ReviewDB::class, parentColumns = ["name"], childColumns = ["reviewName"])
    val reviewName: String,
    val label: String,
    val mark: Int
)

data class ReviewAndSections(
    @Embedded val review: ReviewDB,
    @Relation(
        parentColumn = "name",
        entityColumn = "reviewName"
    )
    val sections: List<SectionDB>
)