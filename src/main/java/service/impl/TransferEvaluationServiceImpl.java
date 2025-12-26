
@Override
public TransferEvaluationResult evaluateTransfer(Long srcId, Long tgtId) {

    Course src = courseRepo.findById(srcId)
            .orElseThrow(() -> new RuntimeException("Source course not found"));

    Course tgt = courseRepo.findById(tgtId)
            .orElseThrow(() -> new RuntimeException("Target course not found"));

    if (!src.isActive() || !tgt.isActive()) {
        throw new IllegalArgumentException("Inactive course");
    }

    List<CourseContentTopic> srcTopics = topicRepo.findByCourseId(srcId);
    List<CourseContentTopic> tgtTopics = topicRepo.findByCourseId(tgtId);

    double overlap = 0.0;

    for (CourseContentTopic s : srcTopics) {
        for (CourseContentTopic t : tgtTopics) {
            if (s.getTopicName().equalsIgnoreCase(t.getTopicName())) {
                overlap += Math.min(
                        s.getWeightPercentage() != null ? s.getWeightPercentage() : 0.0,
                        t.getWeightPercentage() != null ? t.getWeightPercentage() : 0.0
                );
            }
        }
    }

    List<TransferRule> rules =
            ruleRepo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                    src.getUniversity().getId(),
                    tgt.getUniversity().getId()
            );

    boolean eligible = false;
    int creditDiff = Math.abs(src.getCreditHours() - tgt.getCreditHours());

    for (TransferRule r : rules) {
        if (overlap >= r.getMinimumOverlapPercentage()
                && creditDiff <= r.getCreditHourTolerance()) {
            eligible = true;
            break;
        }
    }

    TransferEvaluationResult res = new TransferEvaluationResult();
    res.setSourceCourse(src);
    res.setTargetCourse(tgt);
    res.setOverlapPercentage(overlap);
    res.setCreditHourDifference(creditDiff);
    res.setIsEligibleForTransfer(eligible);
    res.setNotes(eligible ? "Eligible" : "Not eligible");

    return resultRepo.save(res);
}
