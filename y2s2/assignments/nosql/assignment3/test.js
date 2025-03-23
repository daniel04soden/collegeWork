db.students.aggregate([
	{ $match: { gender: "female" } },
	{
		$group: {
			_id: "$region",
			names: { $push: { name: "$name" } },
		},
	},
]);
