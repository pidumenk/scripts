import hudson.model.Hudson
import hudson.model.Project
import hudson.triggers.TimerTrigger
import hudson.triggers.Trigger
import hudson.triggers.TriggerDescriptor

//All the projects on which we can apply the getBuilders method
def allProjects = Hudson.instance.items.findAll { it instanceof Project }
def projectsToWorkOn = [];
allProjects.each { Project project ->
    Map<TriggerDescriptor, Trigger> triggers =
            project.getTriggers();
    triggers.each { trigger ->

        if (trigger.value instanceof TimerTrigger) {
            projectsToWorkOn.push(project)


        }

    }
}


projectsToWorkOn
        .each { Project project ->

    project.disable();
    project.save()
}
