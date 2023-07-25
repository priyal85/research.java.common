package se.cambio.experiment;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import com.google.common.primitives.Ints;
import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.GitCommand;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.TransportCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.transport.SshTransport;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.transport.sshd.IdentityPasswordProvider;
import org.eclipse.jgit.transport.sshd.SshdSessionFactory;
import org.eclipse.jgit.transport.sshd.SshdSessionFactoryBuilder;
import org.eclipse.jgit.util.FS;
import org.eclipse.jgit.util.StringUtils;

public class GitServiceMain
{
  private static final String SSH_DIR = ".ssh";
  private static String userName;
  private static String password;
  private static String passPhrase;

  private static Properties properties;


  public static void main(String[] args) throws GitAPIException, GeneralSecurityException, IOException
  {
    String tempFolderRoot = new File("C:/Temp/Experiment").getAbsolutePath();
    properties = new Properties();
    properties.load(GitServiceMain.class.getResourceAsStream("/App.properties"));
    String localRepo = tempFolderRoot + "\\default-repo";
    String url = properties.getProperty("cambio.env.config.git.repo");
    String label = properties.getProperty("cambio.env.config.git.label");
    userName = properties.getProperty("cambio.env.config.git.user");
    password = properties.getProperty("cambio.env.config.git.password");
    passPhrase = properties.getProperty("cambio.env.config.git.passphrase");

    //System.out.println(localRepo);
    cloneRepository(url,localRepo,label);
  }

  private static void cloneRepository(String url, String localRepo, String label)
    throws GitAPIException, IOException, GeneralSecurityException
  {

    CloneCommand command = Git.cloneRepository().setURI(url).setDirectory(new File(localRepo));
    updateCredentials(command);
    try (Git git = command.call())
    {

      if (null != git && git.getRepository() != null && !StringUtils.isEmptyOrNull(label))
      {
        // Checkout the default branch set for repo in git. This may not always be
        // master. It depends on the
        // admin and organization settings.
        String defaultBranchInGit = git.getRepository().getBranch();
        // If default branch is not empty and NOT equal to defaultLabel, then
        // checkout the branch/tag/commit-id.
        if (!StringUtils.isEmptyOrNull(defaultBranchInGit) && !label.equalsIgnoreCase(defaultBranchInGit))
        {
          checkout(git, label);
        }
      }
    }
  }

  private static Ref checkout(Git git, String label) throws GitAPIException
  {
    CheckoutCommand checkout = git.checkout();
    if (shouldTrack(git, label))
    {
      trackBranch(checkout, label);
    }
    else
    {
      // works for tags and local branches
      checkout.setName(label);
    }
    return checkout.call();
  }

  private static boolean isBranch(Git git, String label) throws GitAPIException
  {
    return containsBranch(git, label, ListBranchCommand.ListMode.ALL);
  }

  private static boolean isLocalBranch(Git git, String label) throws GitAPIException
  {
    return containsBranch(git, label, null);
  }

  private static boolean containsBranch(Git git, String label, ListBranchCommand.ListMode listMode) throws GitAPIException
  {
    ListBranchCommand command = git.branchList();
    if (listMode != null)
    {
      command.setListMode(listMode);
    }
    return command.call().stream().anyMatch(r -> r.getName().endsWith("/" + label));
  }

  private static boolean shouldTrack(Git git, String label) throws GitAPIException
  {
    return isBranch(git, label) && !isLocalBranch(git, label);
  }

  private static void trackBranch(CheckoutCommand checkout, String label)
  {
    checkout.setCreateBranch(true).setName(label).setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.TRACK)
      .setStartPoint("origin/" + label);
  }

  private static  <T extends GitCommand, C> T updateCredentials(TransportCommand<T, C> transportCommand)
    throws GeneralSecurityException
  {
    Integer timeout = Ints.tryParse(System.getProperty("se.cambio.platform.git.command.timeout", "30"));
    transportCommand.setTimeout(timeout != null ? timeout : 30);
    if (!StringUtils.isEmptyOrNull(userName))
    {
      return transportCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(userName, password));
    }
    else if (!StringUtils.isEmptyOrNull(passPhrase))
    {
      String decryptedPassphrase = passPhrase;

      File sshDir = new File(FS.DETECTED.userHome(), File.separator+SSH_DIR);
      SshdSessionFactory sshSessionFactory = new SshdSessionFactoryBuilder().setPreferredAuthentications("publickey")
        .setHomeDirectory(FS.DETECTED.userHome()).setSshDirectory(sshDir)
        .setKeyPasswordProvider(cp -> new IdentityPasswordProvider(cp)
        {
          @Override
          protected char[] getPassword(URIish uri, String message)
          {
            return decryptedPassphrase.toCharArray();
          }
        }).build(null);

      return transportCommand.setTransportConfigCallback(transport -> ((SshTransport) transport).setSshSessionFactory(sshSessionFactory));
    }
    else
    {
      return (T) transportCommand;
    }
  }

//  private static class PassPhraseSessionFactory extends JschConfigSessionFactory
//  {
//    private String passPhrase;
//
//    public PassPhraseSessionFactory(String passPhrase)
//    {
//      super();
//      this.passPhrase = passPhrase;
//    }
//
//    private void setUserName(Session session, String userName) {
//      try {
//        Method
//          setUserNameParentMethod = getClass().getSuperclass().getDeclaredMethod("setUserName", Session.class, String.class);
//        setUserNameParentMethod.setAccessible(true);
//        setUserNameParentMethod.invoke(this, session, userName);
//      } catch (Exception e) {
//       // log.debug("Setting username on secure channel session for GIT ssh connection failed");
//      }
//    }
//    @Override
//    protected JSch createDefaultJSch(FS fs) throws JSchException
//    {
//      JSch jSch = super.createDefaultJSch(fs);
//      final File home = fs.userHome();
//      if (home == null)
//        return jSch;
//      final File sshdir = new File(home, ".ssh"); //$NON-NLS-1$
////      if (sshdir.isDirectory())
////      {
////        Arrays.stream(sshdir.listFiles()).filter(file -> file.isFile() && file.getName().startsWith("id") && !file.getName().endsWith(".pub")).map(file -> file.getAbsolutePath()).forEach(pvk -> {
////          try
////          {
////            jSch.addIdentity(pvk,this.passPhrase);
////          }
////          catch (JSchException e)
////          {
////            throw new RuntimeException(e);
////          }
////        });
////
////      }
//      return jSch;
//    }
//    @Override
//    protected void configure(OpenSshConfig.Host hc, Session session)
//    {
//     // setUserName(session, hc.getUser());
//      session.setUserInfo(new UserInfo()
//      {
//        @Override
//        public String getPassphrase()
//        {
//          return PassPhraseSessionFactory.this.passPhrase;
//        }
//
//        @Override
//        public String getPassword()
//        {
//          return null;
//        }
//
//        @Override
//        public boolean promptPassword(String s)
//        {
//          return false;
//        }
//
//        @Override
//        public boolean promptPassphrase(String s)
//        {
//          return true;
//        }
//
//        @Override
//        public boolean promptYesNo(String s)
//        {
//          return false;
//        }
//
//        @Override
//        public void showMessage(String s)
//        {
//          // Method is not need in this context hence left empty
//        }
//      });
//      session.setConfig("StrictHostKeyChecking", "no");
//    }
//  }
}
