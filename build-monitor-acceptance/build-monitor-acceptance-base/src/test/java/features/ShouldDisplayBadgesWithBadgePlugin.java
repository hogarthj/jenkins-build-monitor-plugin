package features;

import com.github.zafarkhaja.semver.Version;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ShouldDisplayBadgesWithBadgePlugin extends ShouldDisplayBadgesAbstractBase {

    @BeforeClass
    public static void ensureJenkinsVersion() {
        String jenkinsVersionString = System.getenv("JENKINS_VERSION");
        if (jenkinsVersionString == null) {
            Assert.fail("Jenkins version needs to be set as env JENKINS_VERSION");
        }
        Version jenkinsVersion = Version.valueOf(jenkinsVersionString);
        Assume.assumeTrue("Jenkins version too old", jenkinsVersion.greaterThanOrEqualTo(Version.valueOf("2.60.3")));
    }

    @Override
    String[] getPlugins() {
        return new String[] {"buildtriggerbadge", "badge", "groovy-postbuild"};
    }
}
