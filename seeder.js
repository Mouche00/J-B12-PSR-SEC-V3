db = db.getSiblingDB("pigeon_sky_race");

db.breeders.insertMany([
    {
        _id: ObjectId("64b95f4f0f8a4d3d2c6b77f1"),
        username: "breeder1",
        password: "password1",
        loftName: "Loft 1",
        loftCoordinates: { latitude: 40.7128, longitude: -74.0060 },
        finalScore: 90.5,
        pigeons: []
    },
    {
        _id: ObjectId("64b95f4f0f8a4d3d2c6b77f2"),
        username: "breeder2",
        password: "password2",
        loftName: "Loft 2",
        loftCoordinates: { latitude: 34.0522, longitude: -118.2437 },
        finalScore: 88.3,
        pigeons: []
    }
]);

db.pigeons.insertMany([
    {
        _id: ObjectId("64b95f4f0f8a4d3d2c6b77f3"),
        bandNumber: "BN123",
        gender: "Male",
        birthYear: "2020",
        color: "Gray",
        image: "image1.jpg",
        breeder: { "$ref": "breeders", "$id": ObjectId("64b95f4f0f8a4d3d2c6b77f1") },
        rankings: []
    },
    {
        _id: ObjectId("64b95f4f0f8a4d3d2c6b77f4"),
        bandNumber: "BN456",
        gender: "Female",
        birthYear: "2021",
        color: "White",
        image: "image2.jpg",
        breeder: { "$ref": "breeders", "$id": ObjectId("64b95f4f0f8a4d3d2c6b77f2") },
        rankings: []
    }
]);

db.races.insertMany([
    {
        _id: ObjectId("64b95f4f0f8a4d3d2c6b77f5"),
        name: "Race 1",
        startCoordinates: { latitude: 40.7128, longitude: -74.0060 },
        startDate: new Date("2023-05-15T10:00:00Z"),
        targetDistance: 500.0,
        tolerance: 10.0,
        avgDistance: 450.0,
        closedAt: new Date("2023-05-16T18:00:00Z"),
        autoAdj: true,
        rankings: []
    }
]);

db.rankings.insertMany([
    {
        _id: ObjectId("64b95f4f0f8a4d3d2c6b77f6"),
        startTime: ISODate("2023-05-15T10:30:00Z"),
        distance: 500,
        adjustedSpeed: 45.5,
        score: 95,
        pigeon: { "$ref": "pigeons", "$id": ObjectId("64b95f4f0f8a4d3d2c6b77f3") },
        race: { "$ref": "races", "$id": ObjectId("64b95f4f0f8a4d3d2c6b77f5") }
    },
    {
        _id: ObjectId("64b95f4f0f8a4d3d2c6b77f7"),
        startTime: ISODate("2023-06-10T12:00:00Z"),
        distance: 600,
        adjustedSpeed: 48.2,
        score: 98,
        pigeon: { "$ref": "pigeons", "$id": ObjectId("64b95f4f0f8a4d3d2c6b77f4") },
        race: { "$ref": "races", "$id": ObjectId("64b95f4f0f8a4d3d2c6b77f5") }
    }

]);

// db.pigeons.updateOne(
//     { _id: ObjectId("64b95f4f0f8a4d3d2c6b77f3") },
//     { $set: { rankings: [{ "$ref": "rankings", "$id": ObjectId("64b95f4f0f8a4d3d2c6b77f6") }] } }
// );
//
// db.races.updateOne(
//     { _id: ObjectId("64b95f4f0f8a4d3d2c6b77f5") },
//     { $set: { rankings: [{ "$ref": "rankings", "$id": ObjectId("64b95f4f0f8a4d3d2c6b77f6") }] } }
// );
