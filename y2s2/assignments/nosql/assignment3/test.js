db.students.aggregate([
	{ $match: { gender: "female" } },
	{
		$unwind: "$scores",
	},
	{
		$group: {
			_id: {
				region: "$region",
				class: "$class_id",
				studentId: "$_id",
			},
			grades: { $push: "$scores.score" },
		},
	},
	{
		$project: {
			_id: "$_id.region",
			class: "$class_id",
			passedStudents: {
				$size: {
					$filter: {
						input: "$grades",
						as: "passingGrade",
						cond: { $gte: ["$$passingGrade", 40] },
					},
				},
			},
		},
	},
]);
