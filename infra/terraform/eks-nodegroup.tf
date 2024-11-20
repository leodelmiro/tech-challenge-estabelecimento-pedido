resource "aws_eks_node_group" "node-group" {
  cluster_name    = aws_eks_cluster.eks-cluster.name
  node_group_name = "NG-${var.projectName}"
  node_role_arn   = data.aws_iam_role.LabRole.arn
  subnet_ids      = [for subnet in data.aws_subnet.subnet : subnet.id if subnet.availability_zone != "${var.regionDefault}e"]
  instance_types  = [var.instanceType]

  scaling_config {
    desired_size = var.eksDesiredSize
    max_size     = var.eksNodeMaxSize
    min_size     = var.eksNodeMinSize
  }

  update_config {
    max_unavailable = 1
  }
}