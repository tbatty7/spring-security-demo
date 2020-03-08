INSERT INTO users (username, password, enabled)
  values('user', 'awesome', TRUE);

INSERT INTO users (username, password, enabled)
  values('admin', 'admintim', TRUE);

INSERT INTO authorities (username, authority)
  values('user', 'ROLE_USER');

INSERT INTO authorities (username, authority)
  values('admin', 'ROLE_ADMIN');
