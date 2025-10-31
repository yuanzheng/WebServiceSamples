import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import React from 'react';

const Footer: React.FC = () => {
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      copyright="Powered by yTechTrade LLC"
      links={[
        /*{
          key: 'Ant Design Pro',
          title: 'Ant Design Pro',
          href: 'https://pro.ant.design',
          blankTarget: true,
        },*/
        {
          key: 'github',
          title: <GithubOutlined />,
          href: 'https://github.com/yuanzheng/WebServiceSamples',
          blankTarget: true,
        },
        {
          key: 'Ant Design',
          title: 'User Center Branch',
          href: 'https://github.com/yuanzheng/WebServiceSamples/tree/user-center',
          blankTarget: true,
        },
      ]}
    />
  );
};

export default Footer;
